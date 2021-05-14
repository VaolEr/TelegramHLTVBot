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

public class MatchesCallbackQueryHandlerTest extends AbstractCallbackQueryHandlerTest{

    MatchesCallbackQueryHandler matchesCallbackQueryHandler;

    @Override
    @BeforeEach
    void setUp() {
        matchesCallbackQueryHandler = new MatchesCallbackQueryHandler(messageService);
        super.setUp();
    }

    @Override
    @Test
    public void handleCallbackQueryTest() {
        //when
        when(messageService.getReplyMessage(any(), any())).thenReturn(SendMessage.builder()
                .chatId(testChatID)
                .text("query.test")
                .build());

        matchesCallbackQueryHandler.handleCallbackQuery(callbackQuery);

        //then
        assertThat(matchesCallbackQueryHandler.handleCallbackQuery(callbackQuery)).isNotNull();
        assertEquals(SendMessage.builder()
                .chatId(testChatID)
                .text("query.test")
                .build(), matchesCallbackQueryHandler.handleCallbackQuery(callbackQuery));
    }

    @Override
    @Test
    public void handleCallbackQueryMultiAnswerTest() {
        try {
            matchesCallbackQueryHandler.handleCallbackQueryMultiAnswer(callbackQuery);
        } catch (NotImplementedException e){
            assertEquals(NotImplementedException.class, e.getClass());
        }
    }

    @Override
    @Test
    public void getHandlerQueryTypeTest() {
        assertEquals(matchesCallbackQueryHandler.getHandlerQueryType(), BotCallbackQueryType.MATCHES);
    }
}
