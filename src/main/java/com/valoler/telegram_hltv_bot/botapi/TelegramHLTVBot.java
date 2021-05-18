package com.valoler.telegram_hltv_bot.botapi;

import com.valoler.telegram_hltv_bot.botapi.keyboards.TelegramHLTVBotInlineKeyboard;
import com.valoler.telegram_hltv_bot.botapi.keyboards.TelegramHLTVBotReplyKeyboard;
import com.valoler.telegram_hltv_bot.botapi.keyboards.TelegramHLTVBotTeamsInlineKeyboard;
import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.CallbackQueryParser;
import com.valoler.telegram_hltv_bot.model.HltvApiNews;
import com.valoler.telegram_hltv_bot.model.HltvApiResults;
import com.valoler.telegram_hltv_bot.model.HltvApiTeams;
import com.valoler.telegram_hltv_bot.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramHLTVBot extends TelegramWebhookBot {

    private final CallbackQueryParser callbackQueryParser;
    private final TelegramHLTVBotInlineKeyboard telegramHLTVBotInlineKeyboard;
    private final TelegramHLTVBotReplyKeyboard telegramHLTVBotReplyKeyboard;
    private final TelegramHLTVBotTeamsInlineKeyboard telegramHLTVBotTeamsInlineKeyboard;

    private final HltvApiNewsService hltvApiNewsService;
    private final HltvApiResultsService hltvApiResultsService;
    private final HltvApiMatchesService hltvApiMatchesService;
    private final HltvApiStatsbyIdService hltvApiStatsbyIdService;

    public static String COMMAND_PREFIX = "/";

    // This is telegram bot Username
    @Value("${app.bots.usernames.telegramHLTVBotUsername}")
    private String botUsername;

    // This is telegram bot token
    @Value("${app.bots.tokens.telegramHLTVBotToken}")
    private String botToken;

    @Value("${api.bots.webhookPaths.telegramHLTVBotPath}")
    private String botPath;

    @Value("${app.sendMessage.empty}")
    private String emptySendMessageText;

    @Value("${app.sendMessage.NoResultsForTeam}")
    private String noResultsForTeamSendMessageText;

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }

    @Override
    public String getBotPath() {
        return botPath;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

        CallbackQuery callbackQuery;
        Message message;
        String chatId;

        if (update.hasCallbackQuery() && !update.hasMessage()) {
            callbackQuery = update.getCallbackQuery();
            chatId = callbackQuery.getMessage().getChatId().toString();
            message = update.getMessage();
        } else {
            message = update.getMessage();
            chatId = message.getChatId().toString();
            callbackQuery = update.getCallbackQuery();
        }

        if (callbackQuery != null && message == null) {
            log.info("New callbackQuery from User: {} with data: {}", update.getCallbackQuery().getFrom().getUserName(),
                    update.getCallbackQuery().getData());

            //chatId = update.getCallbackQuery().getMessage().getChatId().toString();

            String callbackQueryType = update.getCallbackQuery().getData().split("_")[0];
            //TODO add check for single word callback_queries!
            String callbackTeamName;
            try {
                callbackTeamName = update.getCallbackQuery().getData().split("_")[1];
            } catch (Exception e) {
                log.info(e.getMessage());
                callbackTeamName = "";
            }

            switch (callbackQueryType) {
                case ("TEAMRESULTS"):
                    log.info("{} Results are requested.", callbackTeamName);
                    List<SendMessage> messages = callbackQueryParser.processCallbackQueryMultiAnswer(update.getCallbackQuery());

                    boolean allMessagesAreEmpty = true;

                    for (SendMessage sendMessage : messages) {
                        try {
                            if (!sendMessage.getText().equals(emptySendMessageText)) {
                                allMessagesAreEmpty = false;
                                execute(sendMessage);
                            }
                        } catch (TelegramApiException | NullPointerException e) {
                            log.info(e.fillInStackTrace().toString());
                        }
                    }

                    if (allMessagesAreEmpty) {
                        return SendMessage.builder()
                                .chatId(chatId)
                                .text(noResultsForTeamSendMessageText)
                                .build();
                    }

                    return SendMessage.builder()
                            .chatId(chatId)
                            .text("This is all results what I found for team " + HltvApiTeams.valueOf(callbackTeamName).getHltvApiName() + " !")
                            .build();

                default:
                    return callbackQueryParser.processCallbackQuery(update.getCallbackQuery());
            }
        } else {

            SendMessage sendMessage = new SendMessage();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMMM-d HH:mm:ss", Locale.ENGLISH);

            String userName;
            boolean isBot;
            String messageDateTime;
            String messageText;

            userName = message.getFrom().getUserName();
            isBot = message.getFrom().getIsBot();
            messageDateTime = LocalDateTime.ofEpochSecond(message.getDate(), 0, ZoneOffset.ofHours(3)).format(formatter);
            messageText = message.getText();


            log.info("New message from User: {}, isBot: {}, chatId: {}, date: {}, with text: {}",
                    userName,
                    isBot,
                    chatId,
                    messageDateTime,
                    messageText);

            switch (Objects.requireNonNull(messageText)) {

                case ("/kbd"):
                case ("KBD"):
                    log.debug("Reply keyboard was asked.");
                    return telegramHLTVBotReplyKeyboard.sendReplyKeyBoardMessage(chatId);
                //break;
                case ("/teams"):
                case ("Teams"):
                    log.debug("Inline teams keyboard was asked.");
                    return telegramHLTVBotTeamsInlineKeyboard.sendInlineKeyBoardMessage(chatId);
                //break;
                case ("/news"):
                case ("News"):
                    log.debug("News are requested.");

                    List<HltvApiNews> newsList = hltvApiNewsService.getNews(); // get list of News

                    for (HltvApiNews news : newsList) {
                        try {
                            execute(hltvApiNewsService.prepareNewsMessage(message, news));
                        } catch (TelegramApiException e) {
                            log.info(e.fillInStackTrace().toString());
                            //TODO add return there with message for user about error???
                        }
                    }

                    return SendMessage.builder()
                            .chatId(chatId)
                            .text("This is all news what I found!")
                            .build();
                case ("/results"):
                case ("Results"):
                    log.debug("Results are requested.");

                    List<HltvApiResults> resultsList = hltvApiResultsService.getResults();

                    for (HltvApiResults result : resultsList) {
                        try {
                            execute(hltvApiResultsService.prepareResultsMessage(message, result));
                        } catch (TelegramApiException e) {
                            log.info(e.fillInStackTrace().toString());
                        }
                    }

                    return SendMessage.builder()
                            .chatId(chatId)
                            .text("This is all what I found!")
                            .build();

                default:
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("I don't know that command. Please, type \"/\" for display commands list.");
                    return sendMessage;
            }
        }

    }
}
