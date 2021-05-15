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

public class StatsByMatchIdCallbackQueryHandlerTest extends AbstractCallbackQueryHandlerTest{

    StatsByMatchIdCallbackQueryHandler callbackQueryHandler;

    @Override
    @BeforeEach
    void setUp(){
        callbackQueryHandler = new StatsByMatchIdCallbackQueryHandler(messageService);
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

        callbackQueryHandler.handleCallbackQuery(callbackQuery);

        //then
        assertThat(callbackQueryHandler.handleCallbackQuery(callbackQuery)).isNotNull();
        assertEquals(SendMessage.builder()
                .chatId(testChatID)
                .text("query.test")
                .build(), callbackQueryHandler.handleCallbackQuery(callbackQuery));
    }

    @Override
    @Test
    public void handleCallbackQueryMultiAnswerTest(){
        try {
            callbackQueryHandler.handleCallbackQueryMultiAnswer(callbackQuery);
        } catch (NotImplementedException e){
            assertEquals(NotImplementedException.class, e.getClass());
        }
    }

    @Override
    @Test
    public void getHandlerQueryTypeTest(){
        assertEquals(callbackQueryHandler.getHandlerQueryType(), BotCallbackQueryType.STATS);
    }
}
