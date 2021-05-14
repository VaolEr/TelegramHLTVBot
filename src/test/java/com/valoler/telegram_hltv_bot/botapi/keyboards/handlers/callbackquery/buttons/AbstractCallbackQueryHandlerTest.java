package com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.buttons;

import com.valoler.telegram_hltv_bot.service.ReplyMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;

public abstract class AbstractCallbackQueryHandlerTest {

    final ReplyMessageService messageService = Mockito.mock(ReplyMessageService.class);

    Chat chat;
    Message message;
    CallbackQuery callbackQuery;

    final String testChatID = "123456";

    @BeforeEach
    void setUp(){

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
        //Your test here
    }

    @Test
    public void handleCallbackQueryMultiAnswerTest(){
        //Your test here
    }

    @Test
    public void getHandlerQueryTypeTest(){
        //Your test here
    }

}
