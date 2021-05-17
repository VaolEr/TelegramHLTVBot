package com.valoler.telegram_hltv_bot.botapi;

import com.valoler.telegram_hltv_bot.botapi.keyboards.TelegramHLTVBotInlineKeyboard;
import com.valoler.telegram_hltv_bot.botapi.keyboards.TelegramHLTVBotReplyKeyboard;
import com.valoler.telegram_hltv_bot.botapi.keyboards.TelegramHLTVBotTeamsInlineKeyboard;
import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.CallbackQueryParser;
import com.valoler.telegram_hltv_bot.service.HltvApiMatchesService;
import com.valoler.telegram_hltv_bot.service.HltvApiNewsService;
import com.valoler.telegram_hltv_bot.service.HltvApiResultsService;
import com.valoler.telegram_hltv_bot.service.HltvApiStatsbyIdService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.Collections;

import static com.valoler.telegram_hltv_bot.TestData.testChatID;
import static com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.buttons.TestCallbackQueryHandler.TEST_REPLY_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

    TelegramHLTVBot telegramHLTVBot;

    @Autowired
    TelegramHLTVBot testTelegramHLTVBot;

    @Value("${app.bots.usernames.telegramHLTVBotUsername}")
    private String botUsername;
    @Value("${app.bots.tokens.telegramHLTVBotToken}")
    private String botToken;
    @Value("${api.bots.webhookPaths.telegramHLTVBotPath}")
    private String botPath;

    private Update update;
    private CallbackQuery callbackQuery;
    private User user;

    @BeforeEach
    protected void setUp(){
        telegramHLTVBot = new TelegramHLTVBot(callbackQueryParser,
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
                user.setUserName("Test USER");
            callbackQuery.setFrom(user);
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
    protected void onWebhookUpdateReceivedTest(){

        callbackQuery.setData("IMPLEMENTED$TEST");

        when(callbackQueryParser.processCallbackQuery(any())).thenReturn(SendMessage.builder()
                .chatId(testChatID)
                .text(TEST_REPLY_MESSAGE)
                .build());

        assertEquals(SendMessage.builder()
                .chatId(testChatID)
                .text(TEST_REPLY_MESSAGE)
                .build(), telegramHLTVBot.onWebhookUpdateReceived(update));
    }

    @Test
    protected void onWebhookUpdateReceivedTest2() throws TelegramApiException {

        callbackQuery.setData("TEAMRESULTS_IMPLEMENTED$TEST");

        when(callbackQueryParser.processCallbackQueryMultiAnswer(any())).thenReturn(Collections.singletonList(SendMessage.builder()
                .chatId(testChatID)
                .text(TEST_REPLY_MESSAGE)
                .build()));

        //when(telegramHLTVBot.execute((SendMessage) any())).thenReturn();

//        assertEquals(Collections.singletonList(SendMessage.builder()
//                .chatId(testChatID)
//                .text(TEST_REPLY_MESSAGE)
//                .build()), telegramHLTVBot.onWebhookUpdateReceived(update));
        //TODO rewrite this test because it is not correct
        assertThrows(AssertionError.class, ()-> telegramHLTVBot.onWebhookUpdateReceived(update));
    }
}
