package com.valoler.telegram_hltv_bot.botapi;

import com.valoler.telegram_hltv_bot.botapi.keyboards.TelegramHLTVBotInlineKeyboard;
import com.valoler.telegram_hltv_bot.botapi.keyboards.TelegramHLTVBotReplyKeyboard;
import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.CallbackQueryParser;
import com.valoler.telegram_hltv_bot.service.HltvApiMatchesService;
import com.valoler.telegram_hltv_bot.service.HltvApiNewsService;
import com.valoler.telegram_hltv_bot.service.HltvApiResultsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramHLTVBot extends TelegramWebhookBot {

    private final CallbackQueryParser callbackQueryParser;
    private final TelegramHLTVBotInlineKeyboard telegramHLTVBotInlineKeyboard;
    private final TelegramHLTVBotReplyKeyboard telegramHLTVBotReplyKeyboard;


    private final HltvApiNewsService hltvApiNewsService;
    private final HltvApiResultsService hltvApiResultsService;
    private final HltvApiMatchesService hltvApiMatchesService;

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
            return callbackQueryParser.processCallbackQuery(update.getCallbackQuery());
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

            switch (Objects.requireNonNull(update.getMessage()).getText()){
                case ("KBD"):
                    return telegramHLTVBotInlineKeyboard.sendInlineKeyBoardMessage(message.getChatId().toString());
                    //break;
                case ("/KBD"):
                    log.info("Reply keyboard was asked.");
                    return telegramHLTVBotReplyKeyboard.sendReplyKeyBoardMessage(message.getChatId().toString());
                    //break;
                case ("News"):
                    hltvApiNewsService.getNews();
                    //SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(message.getChatId().toString());
                    sendMessage.setText("News are cooking!");
                    return sendMessage;
                    //break;
                case ("Results"):
                    hltvApiResultsService.getResults();
                    //SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(message.getChatId().toString());
                    sendMessage.setText("Results are cooking!");
                    return sendMessage;
                //break;
                case ("Matches"):
                    hltvApiMatchesService.getMatches();
                    sendMessage.setChatId(message.getChatId().toString());
                    sendMessage.setText("Matches are cooking!");
                    return sendMessage;
                    //break;
                default:
                    sendMessage.setChatId(message.getChatId().toString());
                    sendMessage.setText("Well, all information looks like noise until you break the code.");
                    return sendMessage;
            }
        }

//        if(Objects.requireNonNull(update.getMessage()).getText().equals("KBD")){
//            return telegramHLTVBotInlineKeyboard.sendInlineKeyBoardMessage(message.getChatId().toString());
//        }
//
//        if(Objects.requireNonNull(update.getMessage()).getText().equals("/KBD")){
//            return telegramHLTVBotReplyKeyboard.sendReplyKeyBoardMessage(message.getChatId().toString());
//        }
//
//        if(Objects.requireNonNull(update.getMessage()).getText().equals("News")){
//            hltvApiNewsService.getNews();
//            SendMessage sendMessage = new SendMessage();
//            sendMessage.setChatId(message.getChatId().toString());
//            sendMessage.setText("News are cooking!");
//            return sendMessage;
//        }
//
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            SendMessage sendMessage = new SendMessage();
//            sendMessage.setChatId(message.getChatId().toString());
//            sendMessage.setText("Well, all information looks like noise until you break the code.");
//            return sendMessage;
//        }
        return null;
    }

}
