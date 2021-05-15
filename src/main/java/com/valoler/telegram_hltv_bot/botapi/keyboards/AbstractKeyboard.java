package com.valoler.telegram_hltv_bot.botapi.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractKeyboard {

    public final KeyboardRow getKeyboardButtonsRow(KeyboardButton ... buttons){
        KeyboardRow keyboardRow = new KeyboardRow();
        Collections.addAll(keyboardRow, buttons);
        return keyboardRow;
    }

    public final List<KeyboardRow> getKeyboardAsRowsList(KeyboardRow ... buttonsLists){

        List<KeyboardRow> keyboardAsRowsList = new ArrayList<>();
        Collections.addAll(keyboardAsRowsList, buttonsLists);
        return keyboardAsRowsList;
    }

    public final List<InlineKeyboardButton> getInlineButtonsRow(InlineKeyboardButton ... buttons){
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
        Collections.addAll(buttonsRow, buttons);
        return buttonsRow;
    }

    @SafeVarargs
    public final List<List<InlineKeyboardButton>> getInlineKeyboardAsRowsList(List<InlineKeyboardButton>... buttonsLists){

        List<List<InlineKeyboardButton>> keyboardAsRowsList = new ArrayList<>();
        Collections.addAll(keyboardAsRowsList, buttonsLists);
        return keyboardAsRowsList;
    }

}
