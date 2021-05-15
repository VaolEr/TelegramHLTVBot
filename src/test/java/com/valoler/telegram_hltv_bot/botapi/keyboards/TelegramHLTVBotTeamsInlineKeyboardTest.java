package com.valoler.telegram_hltv_bot.botapi.keyboards;

import com.valoler.telegram_hltv_bot.service.LocaleMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TelegramHLTVBotTeamsInlineKeyboardTest {

    LocaleMessageService localeMessageService = Mockito.mock(LocaleMessageService.class);

    TelegramHLTVBotTeamsInlineKeyboard telegramHLTVBotTeamsInlineKeyboard;

    final String testChatID = "123456";

    InlineKeyboardMarkup inlineKeyboardMarkup;

    @BeforeEach
    public void setUp(){
        telegramHLTVBotTeamsInlineKeyboard = new TelegramHLTVBotTeamsInlineKeyboard(localeMessageService);

        inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton button_team1 = new InlineKeyboardButton();
        InlineKeyboardButton button_team2 = new InlineKeyboardButton();
        InlineKeyboardButton button_team3 = new InlineKeyboardButton();
        InlineKeyboardButton button_team4 = new InlineKeyboardButton();


        button_team1.setText("Natus Vincere");
        button_team1.setCallbackData("TEAMRESULTS_NAVI");

        button_team2.setText("Gambit");
        button_team2.setCallbackData("TEAMRESULTS_GAMBIT");

        button_team3.setText("Astralis");
        button_team3.setCallbackData("TEAMRESULTS_ASTRALIS");

        button_team4.setText("G2");
        button_team4.setCallbackData("TEAMRESULTS_G2");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(button_team1);
        keyboardButtonsRow1.add(button_team2);

        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(button_team3);
        keyboardButtonsRow2.add(button_team4);

        List<List<InlineKeyboardButton>> rowsList = new ArrayList<>();
        rowsList.add(keyboardButtonsRow1);
        rowsList.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowsList);

    }

    //TODO this test work's just for current data structure. If I want to change data, I need to rewrite test.

    @Test
    public void sendInlineKeyBoardMessageTest(){
        when(localeMessageService.getMessage(anyString())).thenReturn("TestString");

        assertEquals(SendMessage.builder().
                chatId(testChatID).
                text("TestString").
                replyMarkup(inlineKeyboardMarkup).
                build(), telegramHLTVBotTeamsInlineKeyboard.sendInlineKeyBoardMessage(testChatID));
    }
}
