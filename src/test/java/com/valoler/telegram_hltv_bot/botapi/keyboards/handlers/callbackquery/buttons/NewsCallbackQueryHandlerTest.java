package com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.buttons;

import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.CallbackQueryType;
import com.valoler.telegram_hltv_bot.service.ReplyMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class NewsCallbackQueryHandlerTest {

    private final ReplyMessageService messageService = Mockito.mock(ReplyMessageService.class);

    Chat chat;
    Message message;
    CallbackQuery callbackQuery;

    NewsCallbackQueryHandler newsCallbackQueryHandler;

    private final String testChatID = "123456";

    @BeforeEach
    void setUp(){
        newsCallbackQueryHandler = new NewsCallbackQueryHandler(messageService);

        callbackQuery = new CallbackQuery();

        chat = new Chat();
        chat.setId(Long.parseLong(testChatID));

        message = new Message();
        message.setChat(chat);
        message.setText("text");
        callbackQuery.setMessage(message);
    }

    @Test
    public void handleCallbackQueryTest(){
        //when
        when(messageService.getReplyMessage(any(), any())).thenReturn(SendMessage.builder()
                .chatId(testChatID)
                .text("query.test")
                .build());

        newsCallbackQueryHandler.handleCallbackQuery(callbackQuery);

        //then
        assertThat(newsCallbackQueryHandler.handleCallbackQuery(callbackQuery)).isNotNull();
        assertEquals(SendMessage.builder()
                .chatId(testChatID)
                .text("query.test")
                .build(), newsCallbackQueryHandler.handleCallbackQuery(callbackQuery));
    }

    @Test
    public void getHandlerQueryTypeTest(){
        assertEquals(newsCallbackQueryHandler.getHandlerQueryType(), CallbackQueryType.NEWS);
    }
}
