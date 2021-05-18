package com.valoler.telegram_hltv_bot.botapi;

import com.valoler.telegram_hltv_bot.botapi.keyboards.TelegramHLTVBotInlineKeyboard;
import com.valoler.telegram_hltv_bot.botapi.keyboards.TelegramHLTVBotReplyKeyboard;
import com.valoler.telegram_hltv_bot.botapi.keyboards.TelegramHLTVBotTeamsInlineKeyboard;
import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.CallbackQueryParser;
import com.valoler.telegram_hltv_bot.model.HltvApiNews;
import com.valoler.telegram_hltv_bot.model.HltvApiResults;
import com.valoler.telegram_hltv_bot.model.HltvApiTeams;
import com.valoler.telegram_hltv_bot.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;

import static com.valoler.telegram_hltv_bot.TestData.*;
import static com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.buttons.TestCallbackQueryHandler.TEST_REPLY_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TelegramHLTVBotTest {

    @MockBean
    CallbackQueryParser callbackQueryParser;

    @MockBean
    TelegramHLTVBotInlineKeyboard telegramHLTVBotInlineKeyboard;

    @MockBean
    TelegramHLTVBotReplyKeyboard telegramHLTVBotReplyKeyboard;

    @MockBean
    TelegramHLTVBotTeamsInlineKeyboard telegramHLTVBotTeamsInlineKeyboard;

    @MockBean
    HltvApiNewsService hltvApiNewsService;

    @MockBean
    HltvApiResultsService hltvApiResultsService;

    @MockBean
    HltvApiMatchesService hltvApiMatchesService;

    @MockBean
    HltvApiStatsbyIdService hltvApiStatsbyIdService;

    @MockBean
    LocaleMessageService localeMessageService;

    TelegramHLTVBot telegramHLTVBot;

    @Autowired
    TelegramHLTVBot testTelegramHLTVBot;

    @Value("${app.bots.usernames.telegramHLTVBotUsername}")
    private String botUsername;
    @Value("${app.bots.tokens.telegramHLTVBotToken}")
    private String botToken;
    @Value("${api.bots.webhookPaths.telegramHLTVBotPath}")
    private String botPath;
    @Value("${app.sendMessage.empty}")
    private String emptySendMessageTextString;
    @Value("${app.sendMessage.NoResultsForTeam}")
    private String noResultsForTeamSendMessageText;


    private Update update;
    private CallbackQuery callbackQuery;
    private User user;
    private Message message;
    private Chat chat;

    @BeforeEach
    protected void setUp(){

        telegramHLTVBot = new TelegramHLTVBot(
                callbackQueryParser,
                telegramHLTVBotInlineKeyboard,
                telegramHLTVBotReplyKeyboard,
                telegramHLTVBotTeamsInlineKeyboard,
                hltvApiNewsService,
                hltvApiResultsService,
                hltvApiMatchesService,
                hltvApiStatsbyIdService);

        update = new Update();
            callbackQuery = new CallbackQuery();
                user = new User();
                user.setId(123456L);
                user.setFirstName("TestUserFirstName");
                user.setIsBot(false);
                user.setUserName("TestUSER");
            callbackQuery.setFrom(user);
                message = new Message();
                message.setMessageId(123);
                message.setText("MessageTestText");
                message.setDate(0);
                    chat = new Chat();
                    chat.setId(Long.parseLong(TEST_CHAT_ID));
                message.setChat(chat);
                message.setFrom(user);
            callbackQuery.setMessage(message);
        update.setCallbackQuery(callbackQuery);
    }


    @Test
    protected void returnCorrectBotUsername(){
        assertEquals(botUsername, testTelegramHLTVBot.getBotUsername());
    }

    @Test
    protected void returnCorrectBotToken(){
        assertEquals(botToken, testTelegramHLTVBot.getBotToken());
    }

    @Test
    protected void returnCorrectBotPath(){
        assertEquals(botPath, testTelegramHLTVBot.getBotPath());
    }

    @Test
    @DisplayName("Return default message if command or message was not recognized.")
    protected void onWebhookUpdateReceivedTest_defaultSwitchState(){

        callbackQuery.setData("IMPLEMENTED$TEST");
        update.setMessage(message);

        when(callbackQueryParser.processCallbackQuery(any())).thenReturn(SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text(TEST_REPLY_MESSAGE)
                .build());

        assertEquals(SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text("I don't know that command. Please, type \"/\" for display commands list.")
                .build(), telegramHLTVBot.onWebhookUpdateReceived(update));
    }
    //

    @Test
    protected void onWebhookUpdateReceivedTest_appSendMessageEmpty(){

        callbackQuery.setData("TEAMRESULTS_IMPLEMENTED$TEST");

        when(callbackQueryParser.processCallbackQueryMultiAnswer(any())).thenReturn(Collections.singletonList(SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text(emptySendMessageTextString)
                .build()));

        assertEquals(SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text(noResultsForTeamSendMessageText)
                .build(), testTelegramHLTVBot.onWebhookUpdateReceived(update));
    }

    @Test
    @DisplayName("Return Reply keyboard on \"/kbd\" command")
    protected void onWebhookUpdateReceivedTest_shouldReturnKeyboardByCommand(){

        message.setText("/kbd");
        update.setMessage(message);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        when(localeMessageService.getMessage(anyString())).thenReturn("\"keyboard.greeting_message\"");

        when(telegramHLTVBotReplyKeyboard.sendReplyKeyBoardMessage(message.getChatId().toString())).thenReturn(SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text("\"keyboard.greeting_message\"")
                .replyMarkup(keyboardMarkup)
                .build());

        assertEquals(SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text("\"keyboard.greeting_message\"")
                .replyMarkup(keyboardMarkup)
                .build(), testTelegramHLTVBot.onWebhookUpdateReceived(update));
    }

    @Test
    @DisplayName("Return Reply keyboard on \"KBD\" message")
    protected void onWebhookUpdateReceivedTest_shouldReturnKeyboardByMessage(){

        message.setText("KBD");
        update.setMessage(message);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        when(localeMessageService.getMessage(anyString())).thenReturn("\"keyboard.greeting_message\"");

        when(telegramHLTVBotReplyKeyboard.sendReplyKeyBoardMessage(message.getChatId().toString())).thenReturn(SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text("\"keyboard.greeting_message\"")
                .replyMarkup(keyboardMarkup)
                .build());

        assertEquals(SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text("\"keyboard.greeting_message\"")
                .replyMarkup(keyboardMarkup)
                .build(), testTelegramHLTVBot.onWebhookUpdateReceived(update));
    }

    @Test
    @DisplayName("Return Inline Teams keyboard on \"/teams\" command")
    protected void onWebhookUpdateReceivedTest_shouldReturnInlineTeamsKeyboardByCommand(){

        message.setText("/teams");
        update.setMessage(message);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        when(localeMessageService.getMessage(anyString())).thenReturn("\"keyboard.greeting_message\"");

        when(telegramHLTVBotTeamsInlineKeyboard.sendInlineKeyBoardMessage(message.getChatId().toString())).thenReturn(SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text("\"keyboard.greeting_message\"")
                .replyMarkup(keyboardMarkup)
                .build());

        assertEquals(SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text("\"keyboard.greeting_message\"")
                .replyMarkup(keyboardMarkup)
                .build(), testTelegramHLTVBot.onWebhookUpdateReceived(update));
    }

    @Test
    @DisplayName("Return Inline Teams keyboard on \"Teams\" message")
    protected void onWebhookUpdateReceivedTest_shouldReturnInlineTeamsKeyboardByMessage(){

        message.setText("Teams");
        update.setMessage(message);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        when(localeMessageService.getMessage(anyString())).thenReturn("\"keyboard.greeting_message\"");

        when(telegramHLTVBotTeamsInlineKeyboard.sendInlineKeyBoardMessage(message.getChatId().toString())).thenReturn(SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text("\"keyboard.greeting_message\"")
                .replyMarkup(keyboardMarkup)
                .build());

        assertEquals(SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text("\"keyboard.greeting_message\"")
                .replyMarkup(keyboardMarkup)
                .build(), testTelegramHLTVBot.onWebhookUpdateReceived(update));
    }

    @Test
    @DisplayName("Return list of news by \"/news\" command")
    protected void onWebhookUpdateReceivedTest_shouldReturnNewsByCommand() throws MalformedURLException {

        message.setText("/news");
        update.setMessage(message);

        List<HltvApiNews> testNewsList = Collections.singletonList(TEST_HLTVAPI_NEWS);

        when(hltvApiNewsService.getNews()).thenReturn(testNewsList);

        assertEquals(SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text("This is all news what I found!")
                .build(), testTelegramHLTVBot.onWebhookUpdateReceived(update));
    }

    @Test
    @DisplayName("Return list of news by \"News\" message")
    protected void onWebhookUpdateReceivedTest_shouldReturnNewsByMessage() throws MalformedURLException {

        message.setText("News");
        update.setMessage(message);

        List<HltvApiNews> testNewsList = Collections.singletonList(TEST_HLTVAPI_NEWS);

        when(hltvApiNewsService.getNews()).thenReturn(testNewsList);

        assertEquals(SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text("This is all news what I found!")
                .build(), testTelegramHLTVBot.onWebhookUpdateReceived(update));
    }

    @Test
    @DisplayName("Return list of news by \"/results\" command")
    protected void onWebhookUpdateReceivedTest_shouldReturnResultsByCommand() {

        message.setText("/results");
        update.setMessage(message);

        List<HltvApiResults> testResultsList = Collections.singletonList(TEST_HLTVAPI_RESULT);

        when(hltvApiResultsService.getResults()).thenReturn(testResultsList);

        assertEquals(SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text("This is all what I found!")
                .build(), testTelegramHLTVBot.onWebhookUpdateReceived(update));
    }

    @Test
    @DisplayName("Return list of news by \"Results\" message")
    protected void onWebhookUpdateReceivedTest_shouldReturnResultsByMessage() {

        message.setText("Results");
        update.setMessage(message);

        List<HltvApiResults> testResultsList = Collections.singletonList(TEST_HLTVAPI_RESULT);

        when(hltvApiResultsService.getResults()).thenReturn(testResultsList);

        assertEquals(SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text("This is all what I found!")
                .build(), testTelegramHLTVBot.onWebhookUpdateReceived(update));
    }

    @Test
    @Disabled
    protected void onWebhookUpdateReceivedTest_MultipleResults() throws TelegramApiException {

        callbackQuery.setData("TEAMRESULTS_IMPLEMENTED$TEST");

        when(callbackQueryParser.processCallbackQueryMultiAnswer(any())).thenReturn(Collections.singletonList(SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text(TEST_REPLY_MESSAGE)
                .build()));

        //when(telegramHLTVBot.execute((SendMessage) any())).thenReturn();

        assertEquals(Collections.singletonList(SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text("This is all results what I found for team " + HltvApiTeams.valueOf("NAVI").getHltvApiName() + " !")
                .build()), testTelegramHLTVBot.onWebhookUpdateReceived(update));
        //TODO rewrite this test because it is not correct
       // assertThrows(TelegramApiException.class, ()-> testTelegramHLTVBot.onWebhookUpdateReceived(update));
    }
}
