package com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.buttons;

import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.BotCallbackQueryType;
import org.apache.maven.surefire.shared.lang3.NotImplementedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class NewsCallbackQueryHandlerTest extends AbstractCallbackQueryHandlerTest {


    NewsCallbackQueryHandler newsCallbackQueryHandler;

    @Override
    @BeforeEach
    void setUp(){
        newsCallbackQueryHandler = new NewsCallbackQueryHandler(messageService);
        super.setUp();
    }

    @Override
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

    @Override
    @Test
    public void handleCallbackQueryMultiAnswerTest(){
        try {
            newsCallbackQueryHandler.handleCallbackQueryMultiAnswer(callbackQuery);
        } catch (NotImplementedException e){
            assertEquals(NotImplementedException.class, e.getClass());
        }
    }

    @Override
    @Test
    public void getHandlerQueryTypeTest(){
        assertEquals(newsCallbackQueryHandler.getHandlerQueryType(), BotCallbackQueryType.NEWS);
    }
}
