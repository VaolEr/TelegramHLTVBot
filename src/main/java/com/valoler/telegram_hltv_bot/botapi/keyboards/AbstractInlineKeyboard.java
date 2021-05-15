package com.valoler.telegram_hltv_bot.botapi.keyboards;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public abstract class AbstractInlineKeyboard extends AbstractKeyboard {

    public abstract SendMessage sendInlineKeyBoardMessage(String chatId);

}
