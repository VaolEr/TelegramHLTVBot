package com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.buttons;

import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.CallbackQueryHandler;
import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.CallbackQueryType;
import com.valoler.telegram_hltv_bot.model.HltvApiResults;
import com.valoler.telegram_hltv_bot.service.HltvApiResultsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TeamResultsCallbackQueryHandler implements CallbackQueryHandler {

    private static final CallbackQueryType HANDLER_QUERY_TYPE = CallbackQueryType.TEAMRESULTS;
    private final HltvApiResultsService hltvApiResultsService;


    private String callbackTeamName ;

    @Override
    public SendMessage handleCallbackQuery(CallbackQuery callbackQuery) {

        return null;

    }

    @Override
    public List<SendMessage> handleCallbackQueryMultiAnswer(CallbackQuery callbackQuery){

         callbackTeamName = callbackQuery.getData().split("_")[1];

        List<SendMessage> sendMessages = new ArrayList<>();
        Message message = callbackQuery.getMessage();

        List<HltvApiResults> resultsList1 = hltvApiResultsService.getResults();

        for(HltvApiResults result : resultsList1){
            sendMessages.add(hltvApiResultsService.prepareResultsMessageOrReturnEmptyMessage(message, result, callbackTeamName));
        }
        return sendMessages;
    }


    @Override
    public CallbackQueryType getHandlerQueryType() {
        return HANDLER_QUERY_TYPE;
    }
}
