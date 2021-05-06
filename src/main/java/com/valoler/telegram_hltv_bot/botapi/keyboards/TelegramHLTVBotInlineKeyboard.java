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
public class TelegramHLTVBotInlineKeyboard {

    private final LocaleMessageService localeMessageService;

    public SendMessage sendInlineKeyBoardMessage(String chatId){

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

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

        button_getStatsByMatchId.setText("Stats (by match id)");
        button_getStatsByMatchId.setCallbackData("STATS");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(button_getNews);
        keyboardButtonsRow1.add(button_getResults);

        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(button_getMatches);
        keyboardButtonsRow2.add(button_getStatsByMatchId);

        List<List<InlineKeyboardButton>> rowsList = new ArrayList<>();
        rowsList.add(keyboardButtonsRow1);
        rowsList.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowsList);

        SendMessage inlineKeyboardMessage = new SendMessage();
        inlineKeyboardMessage.setChatId(chatId);
        inlineKeyboardMessage.setText(localeMessageService.getMessage("keyboard.greeting_message"));
        inlineKeyboardMessage.setReplyMarkup(inlineKeyboardMarkup);

        return inlineKeyboardMessage;
    }

}
