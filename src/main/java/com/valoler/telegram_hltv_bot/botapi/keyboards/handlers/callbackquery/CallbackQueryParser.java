package com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery;

import com.valoler.telegram_hltv_bot.service.ReplyMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Parse the Callback query from telegram bot keyboard
 */
@Component
@RequiredArgsConstructor
public class CallbackQueryParser {

    private final ReplyMessageService messagesService;
    private final List<CallbackQueryHandler> callbackQueryHandlers;

    public SendMessage processCallbackQuery(CallbackQuery usersQuery) {

        CallbackQueryType usersQueryType = CallbackQueryType.valueOf(usersQuery.getData().split("\\|")[0]);

        Optional<CallbackQueryHandler> queryHandler = callbackQueryHandlers.stream().
                filter(callbackQuery -> callbackQuery.getHandlerQueryType().equals(usersQueryType)).findFirst();

        return queryHandler.map(handler -> handler.handleCallbackQuery(usersQuery)).
                orElse(messagesService.getWarningReplyMessage(usersQuery.getMessage().getChatId().toString(), "reply.query.failed"));
    }

    public List<SendMessage> processCallbackQueryMultiAnswer(CallbackQuery usersQuery){
        CallbackQueryType usersQueryType = CallbackQueryType.valueOf(usersQuery.getData().split("_")[0]);

        Optional<CallbackQueryHandler> queryHandler = callbackQueryHandlers.stream().
                filter(callbackQuery -> callbackQuery.getHandlerQueryType().equals(usersQueryType)).findFirst();

        //TODO change to display error message???
        return queryHandler.map(handler -> handler.handleCallbackQueryMultiAnswer(usersQuery)).orElseThrow();

    }

}
