package com.valoler.telegram_hltv_bot.botapi.keyboards;

import com.valoler.telegram_hltv_bot.service.LocaleMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Component
@RequiredArgsConstructor
public class TelegramHLTVBotInlineKeyboard extends AbstractInlineKeyboard {

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

        button_getStatsByMatchId.setText("Stats");
        button_getStatsByMatchId.setCallbackData("STATS");

        inlineKeyboardMarkup.setKeyboard(getInlineKeyboardAsRowsList(
                getInlineButtonsRow(button_getNews, button_getResults),
                getInlineButtonsRow(button_getMatches, button_getStatsByMatchId)
        ));

        SendMessage inlineKeyboardMessage = new SendMessage();
        inlineKeyboardMessage.setChatId(chatId);
        inlineKeyboardMessage.setText(localeMessageService.getMessage("keyboard.greeting_message"));
        inlineKeyboardMessage.setReplyMarkup(inlineKeyboardMarkup);

        return inlineKeyboardMessage;
    }

}
