package com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.buttons;

import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.CallbackQueryHandler;
import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.CallbackQueryType;
import com.valoler.telegram_hltv_bot.service.ReplyMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MatchesCallbackQueryHandler implements CallbackQueryHandler {

    private static final CallbackQueryType HANDLER_QUERY_TYPE = CallbackQueryType.MATCHES;
    private final ReplyMessageService messageService;

    @Override
    public SendMessage handleCallbackQuery(CallbackQuery callbackQuery) {
        final String chatID = callbackQuery.getMessage().getChatId().toString();
        return messageService.getReplyMessage(chatID, "reply.query.MATCHES");
    }

    @Override
    public List<SendMessage> handleCallbackQueryMultiAnswer(CallbackQuery callbackQuery) {
        return null;
    }

    @Override
    public CallbackQueryType getHandlerQueryType() {
        return HANDLER_QUERY_TYPE;
    }

}
