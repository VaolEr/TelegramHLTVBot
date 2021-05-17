package com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery;

import com.valoler.telegram_hltv_bot.service.ReplyMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Parse the Callback query from telegram bot keyboard
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CallbackQueryParser {

    private final ReplyMessageService messagesService;
    private final List<CallbackQueryHandler> callbackQueryHandlers;

    public SendMessage processCallbackQuery(CallbackQuery usersQuery) {

        //CallbackQueryType usersQueryType = BotCallbackQueryType.valueOf(usersQuery.getData().split("\\|")[0]);

        //TODO maybe there is a better way to catch Illegal argument exception???
        CallbackQueryType usersQueryType;

        try {
            usersQueryType = BotCallbackQueryType.valueOf(usersQuery.getData().split("\\|")[0]);
        } catch (IllegalArgumentException e){
            log.info(e.getMessage());
            usersQueryType = BotCallbackQueryType.valueOf("NOT$IMPLEMENTED$");
        }

        CallbackQueryType finalUsersQueryType = usersQueryType;

        Optional<CallbackQueryHandler> queryHandler = callbackQueryHandlers.stream().
                filter(callbackQuery -> callbackQuery.getHandlerQueryType().equals(finalUsersQueryType)).findFirst();

        return queryHandler.map(handler -> handler.handleCallbackQuery(usersQuery)).
                orElse(messagesService.getWarningReplyMessage(usersQuery.getMessage().getChatId().toString(), "reply.query.NOT_FOUND"));
    }

    public List<SendMessage> processCallbackQueryMultiAnswer(CallbackQuery usersQuery){

        //TODO maybe there is a better way to catch Illegal argument exception???
        CallbackQueryType usersQueryType;

        try {
             usersQueryType = BotCallbackQueryType.valueOf(usersQuery.getData().split("_")[0]);
        } catch (IllegalArgumentException e){
            log.info(e.getMessage());
            usersQueryType = BotCallbackQueryType.valueOf("NOT$IMPLEMENTED$");
        }

        CallbackQueryType finalUsersQueryType = usersQueryType;

        Optional<CallbackQueryHandler> queryHandler = callbackQueryHandlers.stream().
                filter(callbackQuery -> callbackQuery.getHandlerQueryType().equals(finalUsersQueryType)).findFirst();

        return queryHandler.map(handler -> handler.handleCallbackQueryMultiAnswer(usersQuery)).
                orElse(Collections.singletonList(messagesService.getWarningReplyMessage(usersQuery.getMessage().getChatId().toString(), "reply.query.NOT_FOUND")));
    }

}
