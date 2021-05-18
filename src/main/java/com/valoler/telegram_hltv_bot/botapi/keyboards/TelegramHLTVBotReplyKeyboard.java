package com.valoler.telegram_hltv_bot.botapi.keyboards;

import com.valoler.telegram_hltv_bot.service.LocaleMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramHLTVBotReplyKeyboard extends AbstractReplyKeyboard{

    private final LocaleMessageService localeMessageService;

    public SendMessage sendReplyKeyBoardMessage(String chatId){

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        // keyboard settings
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(false);

        KeyboardButton button_getNews = new KeyboardButton();
        KeyboardButton button_getResults = new KeyboardButton();
        KeyboardButton button_getMatches = new KeyboardButton();
        //KeyboardButton button_getStatsByMatchId = new KeyboardButton();

        button_getNews.setText("News");
        button_getResults.setText("Results");
        button_getMatches.setText("Teams");
        //button_getStatsByMatchId.setText("Stats");

        replyKeyboardMarkup.setKeyboard(getKeyboardAsRowsList(
                getKeyboardButtonsRow(button_getNews, button_getResults),
                getKeyboardButtonsRow(button_getMatches)
        ));

        SendMessage replyKeyboardMessage = new SendMessage();
        replyKeyboardMessage.setChatId(chatId);
        replyKeyboardMessage.setText(localeMessageService.getMessage("keyboard.greeting_message"));
        replyKeyboardMessage.setReplyMarkup(replyKeyboardMarkup);
        log.debug("Reply keyboard was set.");
        return replyKeyboardMessage;
    }
}
