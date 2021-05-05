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
public class TelegramHLTVBotReplyKeyboard {

    private final LocaleMessageService localeMessageService;

//    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//
//    List<KeyboardRow> keyboard = new ArrayList<>();
//
//    KeyboardRow keyboardRow1 = new KeyboardRow();
//    KeyboardRow keyboardRow2 = new KeyboardRow();
//
//    KeyboardButton button_getNews = new KeyboardButton();
//    KeyboardButton button_getResults = new KeyboardButton();
//    KeyboardButton button_getMatches = new KeyboardButton();
//    KeyboardButton button_getStatsByMatchId = new KeyboardButton();

    public SendMessage sendReplyKeyBoardMessage(String chatId){

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardRow1 = new KeyboardRow();
        KeyboardRow keyboardRow2 = new KeyboardRow();

        KeyboardButton button_getNews = new KeyboardButton();
        KeyboardButton button_getResults = new KeyboardButton();
        KeyboardButton button_getMatches = new KeyboardButton();
        KeyboardButton button_getStatsByMatchId = new KeyboardButton();

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(false);

        button_getNews.setText("News");
        button_getResults.setText("Results");
        button_getMatches.setText("Matches");
        button_getStatsByMatchId.setText("Stats (by match id)");

        keyboardRow1.add(button_getNews);
        keyboardRow1.add(button_getResults);

        keyboardRow2.add(button_getMatches);
        keyboardRow2.add(button_getStatsByMatchId);

        keyboard.add(keyboardRow1);
        keyboard.add(keyboardRow2);

        replyKeyboardMarkup.setKeyboard(keyboard);

        SendMessage replyKeyboardMessage = new SendMessage();
        replyKeyboardMessage.setChatId(chatId);
        replyKeyboardMessage.setReplyMarkup(replyKeyboardMarkup);
        log.debug("Reply keyboard was set.");
        return replyKeyboardMessage;
    }
}
