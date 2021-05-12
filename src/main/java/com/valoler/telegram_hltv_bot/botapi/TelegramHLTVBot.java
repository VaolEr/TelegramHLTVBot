package com.valoler.telegram_hltv_bot.botapi;

import com.valoler.telegram_hltv_bot.botapi.keyboards.TelegramHLTVBotInlineKeyboard;
import com.valoler.telegram_hltv_bot.botapi.keyboards.TelegramHLTVBotReplyKeyboard;
import com.valoler.telegram_hltv_bot.botapi.keyboards.TelegramHLTVBotTeamsInlineKeyboard;
import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.CallbackQueryParser;
import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.CallbackQueryType;
import com.valoler.telegram_hltv_bot.model.HltvApiNews;
import com.valoler.telegram_hltv_bot.model.HltvApiResults;
import com.valoler.telegram_hltv_bot.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
@PropertySource("classpath:application-botSettings.properties")
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

        if (update.hasCallbackQuery()) {
            log.info("New callbackQuery from User: {} with data: {}", update.getCallbackQuery().getFrom().getUserName(),
                    update.getCallbackQuery().getData());

            String callbackQueryType = update.getCallbackQuery().getData().split("_")[0];
            String callbackTeamName = update.getCallbackQuery().getData().split("_")[1];

            switch (callbackQueryType) {
                case ("TEAMRESULTS"):
                    log.info("{} Results are requested.", callbackTeamName);
                    List<SendMessage> messages = callbackQueryParser.processCallbackQueryMultiAnswer(update.getCallbackQuery());
                    for (SendMessage sendMessage : messages) {
                        try {
                            if(!sendMessage.getText().equals("app.sendMessage.empty")) {
                                execute(sendMessage);
                            }
                        } catch (TelegramApiException | NullPointerException e) {
                            log.info(e.fillInStackTrace().toString());
                        }
                    }
                    break;
                default:
                    return callbackQueryParser.processCallbackQuery(update.getCallbackQuery());
            }
        }

        Message message = update.getMessage();

        SendMessage sendMessage = new SendMessage();

        if (message != null && message.hasText()) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMMM-d HH:mm:ss", Locale.ENGLISH);
            log.info("New message from User: {}, isBot: {}, chatId: {}, date: {}, with text: {}",
                    message.getFrom().getUserName(),
                    message.getFrom().getIsBot(),
                    message.getChatId(),
                    LocalDateTime.ofEpochSecond(message.getDate(), 0, ZoneOffset.ofHours(3)).format(formatter),
                    message.getText());

            switch (Objects.requireNonNull(update.getMessage()).getText()) {
                case ("KBD"):
                    log.debug("Inline keyboard was asked.");
                    return telegramHLTVBotInlineKeyboard.sendInlineKeyBoardMessage(message.getChatId().toString());
                case ("/teams"):
                    log.debug("Inline teams keyboard was asked.");
                    return telegramHLTVBotTeamsInlineKeyboard.sendInlineKeyBoardMessage(message.getChatId().toString());
                //break;
                case ("/KBD"):
                    log.debug("Reply keyboard was asked.");
                    return telegramHLTVBotReplyKeyboard.sendReplyKeyBoardMessage(message.getChatId().toString());
                //break;
                case ("News"):
                    log.debug("News are requested.");

                    List<HltvApiNews> newsList = hltvApiNewsService.getNews(); // get list of News

                    for (HltvApiNews news : newsList) {
                        try {
                            execute(hltvApiNewsService.prepareNewsMessage(message, news));
                        } catch (TelegramApiException e) {
                            log.info(e.fillInStackTrace().toString());
                        }
                    }

                    break;
                case ("Results"):
                    log.debug("Results are requested.");

                    List<HltvApiResults> resultsList = hltvApiResultsService.getResults();

                    for(HltvApiResults result : resultsList){
                        try {
                            execute(hltvApiResultsService.prepareResultsMessage(message, result));
                        } catch (TelegramApiException e) {
                            log.info(e.fillInStackTrace().toString());
                        }
                    }

                    break;

                case ("Matches"):
                    log.debug("Matches are requested.");
                    hltvApiMatchesService.getMatches();
                    sendMessage.setChatId(message.getChatId().toString());
                    sendMessage.setText("Matches are cooking!");
                    return sendMessage;
                //break;
                case ("Stats"):
                    log.debug("Stats are requested.");
                    hltvApiStatsbyIdService.getStats();
                    sendMessage.setChatId(message.getChatId().toString());
                    sendMessage.setText("For get statistic type '/getStats+/matches/matchId/matchFullName'");
                    return sendMessage;
                default:
                    sendMessage.setChatId(message.getChatId().toString());
                    sendMessage.setText("Well, all information looks like noise until you break the code.");
                    return sendMessage;
            }
        }
        return null;
    }
}
