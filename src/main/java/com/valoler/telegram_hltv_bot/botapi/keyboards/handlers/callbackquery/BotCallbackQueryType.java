package com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery;

/**
 * Callback Query types
 * @author ValolEr
 */
public enum BotCallbackQueryType implements CallbackQueryType {
    NEWS,
    RESULTS,
    MATCHES,
    STATS,

    TEAMRESULTS,

    IMPLEMENTED$TEST,
    NOT$IMPLEMENTED$
    ;
}
