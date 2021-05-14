package com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery;

import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.buttons.NewsCallbackQueryHandler;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CallbackQueryParserTest {

    private final ReplyMessageService messageService = Mockito.mock(ReplyMessageService.class);
    private CallbackQuery callbackQuery;// = Mockito.mock(CallbackQuery.class);

    private NewsCallbackQueryHandler newsCallbackQueryHandler ;//= Mockito.mock(NewsCallbackQueryHandler.class);

    List<CallbackQueryHandler> callbackQueryHandlers = new ArrayList<>();

    CallbackQueryParser callbackQueryParser;

    private final String testChatID = "123456";
    Chat chat;
    Message message;

    @BeforeEach
    public void setUp(){
        callbackQuery = new CallbackQuery();
        newsCallbackQueryHandler = new NewsCallbackQueryHandler(messageService);
        callbackQueryHandlers.add(newsCallbackQueryHandler);

        callbackQueryParser = new CallbackQueryParser(messageService, callbackQueryHandlers);




        chat = new Chat();
        chat.setId(Long.parseLong(testChatID));

        message = new Message();
        message.setChat(chat);
        message.setText("text");

        callbackQuery.setData("NEWS");
        callbackQuery.setMessage(message);

    }

    @Test
    @Disabled
    public void processCallbackQueryTest(){
        //when
        //when(BotCallbackQueryType.valueOf(callbackQuery.getData().split("\\|")[0])).thenReturn(BotCallbackQueryType.TEST);

//        when(newsCallbackQueryHandler.handleCallbackQuery(any())).thenReturn(SendMessage.builder()
//                .chatId(testChatID)
//                .text("reply.query.failed")
//                .build());

        when(messageService.getReplyMessage(anyString(), anyString())).thenReturn(SendMessage.builder()
                .chatId(testChatID)
                .text("reply.query.failed")
                .build());
        SendMessage acd = callbackQueryParser.processCallbackQuery(callbackQuery);
        assertEquals(SendMessage.builder()
                .chatId(testChatID)
                .text("reply.query.failed")
                .build(), callbackQueryParser.processCallbackQuery(callbackQuery));
    }
}
