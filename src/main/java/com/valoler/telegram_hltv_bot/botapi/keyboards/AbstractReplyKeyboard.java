package com.valoler.telegram_hltv_bot.botapi.keyboards;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public abstract class AbstractReplyKeyboard extends AbstractKeyboard {

    public abstract SendMessage sendReplyKeyBoardMessage(String chatId);

}
