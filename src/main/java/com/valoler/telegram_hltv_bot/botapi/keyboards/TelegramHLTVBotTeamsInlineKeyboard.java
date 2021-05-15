package com.valoler.telegram_hltv_bot.botapi.keyboards;

import com.valoler.telegram_hltv_bot.service.LocaleMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TelegramHLTVBotTeamsInlineKeyboard extends AbstractInlineKeyboard{

    private final LocaleMessageService localeMessageService;

    public SendMessage sendInlineKeyBoardMessage(String chatId){

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

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

        inlineKeyboardMarkup.setKeyboard(getInlineKeyboardAsRowsList(
                getInlineButtonsRow(button_team1, button_team2),
                getInlineButtonsRow(button_team3, button_team4)
        ));

        SendMessage inlineKeyboardMessage = new SendMessage();
        inlineKeyboardMessage.setChatId(chatId);
        inlineKeyboardMessage.setText(localeMessageService.getMessage("inline.keyboard.teamsResults.greeting_message"));
        inlineKeyboardMessage.setReplyMarkup(inlineKeyboardMarkup);

        return inlineKeyboardMessage;
    }

}
