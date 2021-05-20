package com.valoler.telegram_hltv_bot.service;

import com.valoler.telegram_hltv_bot.dto.HltvApiResultsTo;
import com.valoler.telegram_hltv_bot.model.HltvApiResults;
import com.valoler.telegram_hltv_bot.model.HltvApiTeam;
import com.valoler.telegram_hltv_bot.model.HltvApiTeams;
import com.valoler.telegram_hltv_bot.util.HltvApiResultsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static com.valoler.telegram_hltv_bot.TestData.TEST_CHAT_ID;
import static com.valoler.telegram_hltv_bot.TestData.TEST_HLTVAPI_RESULT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest()
public class HltvApiResultsServiceTest extends AbstractServiceTest{

    String path = "/results";

    HltvApiResults hltvApiResults;
    List<HltvApiResults> resultsList;
    HltvApiResultsTo[] resultsTos;

    @InjectMocks
    HltvApiResultsService resultsService;

    String emptySendMessageText = "//emptyMessage//";

    SendMessage sendMessage ;
    @BeforeEach
    protected void setUp() throws MalformedURLException {

        super.setUp();

        ReflectionTestUtils.setField(resultsService, "emptySendMessageText", "//emptyMessage//");

        hltvApiResults = TEST_HLTVAPI_RESULT;
        resultsList = new ArrayList<>();
        resultsList.add(hltvApiResults);
        resultsTos = new HltvApiResultsTo[1];
        resultsTos[0] = HltvApiResultsUtil.toHltvApiResultsTo(hltvApiResults);

        sendMessage = new SendMessage();
    }

    @Test
    @DisplayName("Check that context is creating HltvApiNewsService")
    public void contextLoads() {
        assertThat(resultsService).isNotNull();
    }

    @Test
    @DisplayName("Should return correct results list")
    public void hltvApiResultsService_getResultsTest(){

        ResponseEntity<HltvApiResultsTo[]> response = new ResponseEntity<HltvApiResultsTo[]>(resultsTos, HttpStatus.ACCEPTED);

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(HltvApiResultsTo[].class))).thenReturn(response);
        assertEquals(resultsList.get(0).getEvent(), resultsService.getResults().get(0).getEvent());
        assertEquals(resultsList.get(0).getMaps(), resultsService.getResults().get(0).getMaps());
        assertEquals(resultsList.get(0).getMatchId(), resultsService.getResults().get(0).getMatchId());
        assertEquals(resultsList.get(0).getTeam1().getName(), resultsService.getResults().get(0).getTeam1().getName());
        assertEquals(resultsList.get(0).getTeam2().getName(), resultsService.getResults().get(0).getTeam2().getName());
        assertEquals(resultsList.get(0).getTime(), resultsService.getResults().get(0).getTime());
    }

    @Test
    @DisplayName("Should return correct message from prepareResultsMessage")
    public void hltvApiResultsService_prepareResultsMessageTest(){
        when(message.getChatId()).thenReturn(123456L);
        when(localeMessageService.getMessage(anyString())).thenReturn("Test button text");

        sendMessage.setChatId(TEST_CHAT_ID);
        sendMessage.setReplyMarkup(resultsService.getInlineKeyboard(hltvApiResults));
        sendMessage.enableMarkdown(true);
        sendMessage.setText(resultsService.prepareMessageText(hltvApiResults));

        assertEquals(sendMessage, resultsService.prepareResultsMessage(message, hltvApiResults));
    }

    @Test
    @DisplayName("Should return correct message")
    public void hltvApiResultsService_prepareResultsMessageOrReturnEmptyMessageTest(){

        when(message.getChatId()).thenReturn(123456L);
        when(localeMessageService.getMessage(anyString())).thenReturn("Test button text");

        sendMessage.setChatId(TEST_CHAT_ID);
        sendMessage.setReplyMarkup(resultsService.getInlineKeyboard(hltvApiResults));
        sendMessage.enableMarkdown(true);
        sendMessage.setText(resultsService.prepareMessageText(hltvApiResults));

        assertEquals(sendMessage, resultsService.prepareResultsMessageOrReturnEmptyMessage(message, hltvApiResults, "NAVI"));
    }

    @Test
    @DisplayName("Should return correct empty message")
    public void hltvApiResultsService_prepareResultsMessageOrReturnEmptyMessageTest2() throws CloneNotSupportedException {

        HltvApiTeam testTeam = new HltvApiTeam();
        testTeam.setName("TestTeam");
        HltvApiResults result = (HltvApiResults) TEST_HLTVAPI_RESULT.clone();
        result.setTeam1(testTeam);

        when(message.getChatId()).thenReturn(123456L);
        when(localeMessageService.getMessage(anyString())).thenReturn("Test button text");

        sendMessage = new SendMessage();
        sendMessage.setChatId(TEST_CHAT_ID);
        sendMessage.setText(emptySendMessageText);

        assertEquals(sendMessage, resultsService.prepareResultsMessageOrReturnEmptyMessage(message, result, "NAVI"));
    }
}
