package com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery;

import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.buttons.MatchesCallbackQueryHandler;
import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.buttons.NewsCallbackQueryHandler;
import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.buttons.ResultsCallbackQueryHandler;
import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.buttons.TestCallbackQueryHandler;
import com.valoler.telegram_hltv_bot.service.ReplyMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.buttons.TestCallbackQueryHandler.TEST_REPLY_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CallbackQueryParserTest {

    private final ReplyMessageService messageService = Mockito.mock(ReplyMessageService.class);
    private CallbackQuery callbackQuery;

    List<CallbackQueryHandler> callbackQueryHandlers = new ArrayList<>();

    CallbackQueryParser callbackQueryParser;

    private final String testChatID = "123456";
    Chat chat;
    Message message;

    @BeforeEach
    public void setUp(){

        callbackQuery = new CallbackQuery();

        callbackQueryHandlers.add(new NewsCallbackQueryHandler(messageService));
        callbackQueryHandlers.add(new MatchesCallbackQueryHandler(messageService));
        callbackQueryHandlers.add(new ResultsCallbackQueryHandler(messageService));
        callbackQueryHandlers.add(new TestCallbackQueryHandler(messageService));

        callbackQueryParser = new CallbackQueryParser(messageService, callbackQueryHandlers);

        chat = new Chat();
        chat.setId(Long.parseLong(testChatID));

        message = new Message();
        message.setChat(chat);
        message.setText(TEST_REPLY_MESSAGE);

        callbackQuery.setMessage(message);
    }

    @Test
    public void processCallbackQueryTest(){

        callbackQuery.setData("IMPLEMENTED$TEST");

        assertEquals(SendMessage.builder()
                .chatId(testChatID)
                .text(TEST_REPLY_MESSAGE)
                .build(), callbackQueryParser.processCallbackQuery(callbackQuery));
    }

    @Test
    public void processCallbackQueryNotFoundTest(){
        callbackQuery.setData("NOT$IMPLEMENTED$");

        when(messageService.getWarningReplyMessage(anyString(), anyString())).thenReturn(SendMessage.builder()
                .chatId(testChatID)
                .text("reply.query.NOT_FOUND")
                .build());

        assertEquals(SendMessage.builder()
                .chatId(testChatID)
                .text("reply.query.NOT_FOUND")
                .build(), callbackQueryParser.processCallbackQuery(callbackQuery));
    }

    @Test
    public void processCallbackQueryMultiAnswerTest(){
        callbackQuery.setData("IMPLEMENTED$TEST");

        assertEquals(Collections.singletonList(SendMessage.builder()
                .chatId(testChatID)
                .text(TEST_REPLY_MESSAGE)
                .build()), callbackQueryParser.processCallbackQueryMultiAnswer(callbackQuery));
    }

    @Test
    public void processCallbackQueryMultiAnswerNotFoundTest(){
        callbackQuery.setData("NOT$IMPLEMENTED$");
        when(messageService.getWarningReplyMessage(anyString(), anyString())).thenReturn(SendMessage.builder()
                .chatId(testChatID)
                .text("reply.query.NOT_FOUND")
                .build());

        assertEquals(Collections.singletonList(SendMessage.builder()
                .chatId(testChatID)
                .text("reply.query.NOT_FOUND")
                .build()), callbackQueryParser.processCallbackQueryMultiAnswer(callbackQuery));
    }

}
