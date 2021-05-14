package com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.buttons;

import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.BotCallbackQueryType;
import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.CallbackQueryHandler;
import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.CallbackQueryType;
import com.valoler.telegram_hltv_bot.service.ReplyMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.surefire.shared.lang3.NotImplementedException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewsCallbackQueryHandler implements CallbackQueryHandler {

    private static final CallbackQueryType HANDLER_QUERY_TYPE = BotCallbackQueryType.NEWS;
    private final ReplyMessageService messageService;

    @Override
    public SendMessage handleCallbackQuery(CallbackQuery callbackQuery) {
        final String chatID = callbackQuery.getMessage().getChatId().toString();
        return messageService.getReplyMessage(chatID, "reply.query.NEWS");
    }

    @Override
    public List<SendMessage> handleCallbackQueryMultiAnswer(CallbackQuery callbackQuery) {
        throw new NotImplementedException("handleCallbackQueryMultiAnswer not implemented in " + NewsCallbackQueryHandler.class);
    }

    @Override
    public CallbackQueryType getHandlerQueryType() {
        return HANDLER_QUERY_TYPE;
    }
}
