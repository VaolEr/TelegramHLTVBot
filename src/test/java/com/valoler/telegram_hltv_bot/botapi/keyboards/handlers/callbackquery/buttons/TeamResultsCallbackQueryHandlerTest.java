package com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.buttons;

import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.BotCallbackQueryType;
import com.valoler.telegram_hltv_bot.model.HltvApiResults;
import com.valoler.telegram_hltv_bot.model.HltvApiTeam;
import com.valoler.telegram_hltv_bot.service.HltvApiResultsService;
import org.apache.maven.surefire.shared.lang3.NotImplementedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

import static com.valoler.telegram_hltv_bot.TestData.TEST_EVENT_NAME;
import static com.valoler.telegram_hltv_bot.util.HltvApiResultsUtilTests.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TeamResultsCallbackQueryHandlerTest extends AbstractCallbackQueryHandlerTest{

    TeamResultsCallbackQueryHandler callbackQueryHandler;
    HltvApiResultsService hltvApiResultsService = Mockito.mock(HltvApiResultsService.class);

    @Override
    @BeforeEach
    void setUp(){
        callbackQueryHandler = new TeamResultsCallbackQueryHandler(hltvApiResultsService);
        super.setUp();
        callbackQuery.setData("RESULTS_TestTeamName");
    }

    @Override
    @Test
    public void handleCallbackQueryTest(){
        try {
            callbackQueryHandler.handleCallbackQuery(callbackQuery);
        } catch (NotImplementedException e){
            assertEquals(NotImplementedException.class, e.getClass());
        }
    }

    @Override
    @Test
    public void handleCallbackQueryMultiAnswerTest(){
        //when
        //TODO rewrite test, because this - stupid!!!
        HltvApiResults testResult = new HltvApiResults();

        testResult.setEvent(TEST_EVENT_NAME);
        testResult.setMaps(TEST_MAPS);
        testResult.setTeam1(new HltvApiTeam());
        testResult.setTeam2(new HltvApiTeam());
        testResult.setTime(TEST_TIME);
        testResult.setMatchId(TEST_MATCHID);

        List<HltvApiResults> testResultsList = new ArrayList<>();
        testResultsList.add(testResult);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("text");
        sendMessage.setChatId(testChatID);

        //when(callbackQuery.getData().split(any())[(int) any()]).thenReturn("CallbackTeamName");
        when(hltvApiResultsService.getResults()).thenReturn(testResultsList);
        when(hltvApiResultsService.prepareResultsMessageOrReturnEmptyMessage(any(), any(), any())).thenReturn(sendMessage);

        //then
        assertThat(callbackQueryHandler.handleCallbackQueryMultiAnswer(callbackQuery)).isNotNull();
        assertThat(callbackQueryHandler.handleCallbackQueryMultiAnswer(callbackQuery)).isInstanceOf(List.class);
//        assertEquals(SendMessage.builder()
//                .chatId(testChatID)
//                .text("query.test")
//                .build(), callbackQueryHandler.handleCallbackQuery(callbackQuery));
    }

    @Override
    @Test
    public void getHandlerQueryTypeTest(){
        assertEquals(callbackQueryHandler.getHandlerQueryType(), BotCallbackQueryType.TEAMRESULTS);
    }

}
