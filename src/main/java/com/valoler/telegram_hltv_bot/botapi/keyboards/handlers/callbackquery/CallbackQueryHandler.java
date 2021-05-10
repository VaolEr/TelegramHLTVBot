package com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.List;

public interface CallbackQueryHandler {
    SendMessage handleCallbackQuery(CallbackQuery callbackQuery);

    List<SendMessage> handleCallbackQueryMultiAnswer(CallbackQuery callbackQuery);

    CallbackQueryType getHandlerQueryType();
}
