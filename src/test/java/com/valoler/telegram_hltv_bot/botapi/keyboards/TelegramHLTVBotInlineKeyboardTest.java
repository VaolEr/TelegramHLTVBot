package com.valoler.telegram_hltv_bot.botapi.keyboards;

import com.valoler.telegram_hltv_bot.service.LocaleMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TelegramHLTVBotInlineKeyboardTest {

    LocaleMessageService localeMessageService = Mockito.mock(LocaleMessageService.class);

    TelegramHLTVBotInlineKeyboard telegramHLTVBotInlineKeyboard;

    InlineKeyboardMarkup inlineKeyboardMarkup;

    final String testChatID = "123456";

    @BeforeEach
    public void setUp(){
        telegramHLTVBotInlineKeyboard = new TelegramHLTVBotInlineKeyboard(localeMessageService);

        inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton button_getNews = new InlineKeyboardButton();
        InlineKeyboardButton button_getResults = new InlineKeyboardButton();
        InlineKeyboardButton button_getMatches = new InlineKeyboardButton();
        InlineKeyboardButton button_getStatsByMatchId = new InlineKeyboardButton();

        button_getNews.setText("News");
        button_getNews.setCallbackData("NEWS");

        button_getResults.setText("Results");
        button_getResults.setCallbackData("RESULTS");

        button_getMatches.setText("Matches");
        button_getMatches.setCallbackData("MATCHES");

        button_getStatsByMatchId.setText("Stats");
        button_getStatsByMatchId.setCallbackData("STATS");

        inlineKeyboardMarkup.setKeyboard(telegramHLTVBotInlineKeyboard.getInlineKeyboardAsRowsList(
                telegramHLTVBotInlineKeyboard.getInlineButtonsRow(button_getNews, button_getResults),
                telegramHLTVBotInlineKeyboard.getInlineButtonsRow(button_getMatches, button_getStatsByMatchId)
        ));
    }

    @Test
    public void sendInlineKeyBoardMessageTest(){
        when(localeMessageService.getMessage(anyString())).thenReturn("TestString");

        assertEquals(SendMessage.class, telegramHLTVBotInlineKeyboard.sendInlineKeyBoardMessage(testChatID).getClass());

        assertEquals(SendMessage.builder().
                chatId(testChatID).
                text("TestString").
                replyMarkup(inlineKeyboardMarkup).
                build(), telegramHLTVBotInlineKeyboard.sendInlineKeyBoardMessage(testChatID));

    }

}
