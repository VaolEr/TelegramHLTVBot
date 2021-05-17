package com.valoler.telegram_hltv_bot.botapi.keyboards;

import com.valoler.telegram_hltv_bot.service.LocaleMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TelegramHLTVBotReplyKeyboardTest {

    private final LocaleMessageService localeMessageService = Mockito.mock(LocaleMessageService.class);

    TelegramHLTVBotReplyKeyboard telegramHLTVBotReplyKeyboard;

    final String testChatID = "123456";

    ReplyKeyboardMarkup replyKeyboardMarkup;

    @BeforeEach
    public void setUp(){
        telegramHLTVBotReplyKeyboard = new TelegramHLTVBotReplyKeyboard(localeMessageService);

        replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(false);

        KeyboardButton button_getNews = new KeyboardButton();
        button_getNews.setText("News");

        KeyboardButton button_getResults = new KeyboardButton();
        button_getResults.setText("Results");

        KeyboardButton button_getMatches = new KeyboardButton();
        button_getMatches.setText("Matches");

        KeyboardButton button_getStatsByMatchId = new KeyboardButton();
        button_getStatsByMatchId.setText("Stats");

        replyKeyboardMarkup.setKeyboard(telegramHLTVBotReplyKeyboard.getKeyboardAsRowsList(
                telegramHLTVBotReplyKeyboard.getKeyboardButtonsRow(button_getNews, button_getResults),
                telegramHLTVBotReplyKeyboard.getKeyboardButtonsRow(button_getMatches, button_getStatsByMatchId)
        ));
    }

    @Test
    public void sendReplyKeyBoardMessageTest(){
        when(localeMessageService.getMessage(anyString())).thenReturn("TestString");

        assertEquals(SendMessage.class, telegramHLTVBotReplyKeyboard.sendReplyKeyBoardMessage(testChatID).getClass());

        assertEquals(SendMessage.builder().
                chatId(testChatID).
                text("TestString").
                replyMarkup(replyKeyboardMarkup).
                build(), telegramHLTVBotReplyKeyboard.sendReplyKeyBoardMessage(testChatID));

    }


}
