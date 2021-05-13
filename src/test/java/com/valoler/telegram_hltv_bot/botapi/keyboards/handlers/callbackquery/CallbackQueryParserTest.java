package com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery;

import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.buttons.NewsCallbackQueryHandler;
import com.valoler.telegram_hltv_bot.service.ReplyMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;

public class CallbackQueryParserTest {

    private final ReplyMessageService messageService = Mockito.mock(ReplyMessageService.class);
    private final CallbackQuery callbackQuery = Mockito.mock(CallbackQuery.class);

    private final NewsCallbackQueryHandler newsCallbackQueryHandler = Mockito.mock(NewsCallbackQueryHandler.class);

    List<CallbackQueryHandler> callbackQueryHandlers = new ArrayList<>();
    CallbackQueryParser callbackQueryParser;

    private final String testChatID = "123456";
    Chat chat;
    Message message;

    @BeforeEach
    public void setUp(){
        callbackQueryHandlers.add(newsCallbackQueryHandler);

        callbackQueryParser = new CallbackQueryParser(messageService, callbackQueryHandlers);

        chat = new Chat();
        chat.setId(Long.parseLong(testChatID));

        message = new Message();
        message.setChat(chat);
        message.setText("text");
    }

    @Test
    @Disabled
    public void processCallbackQueryTest(){

    }
}
