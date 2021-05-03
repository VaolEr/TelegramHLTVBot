package com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface CallbackQueryHandler {
    SendMessage handleCallbackQuery(CallbackQuery callbackQuery);

    CallbackQueryType getHandlerQueryType();
}
