package com.valoler.telegram_hltv_bot.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.valoler.telegram_hltv_bot.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HltvApiResultsToTest {

    HltvApiResultsTo resultsTo;

    @BeforeEach
    protected void setUp(){
        resultsTo = new HltvApiResultsTo(null, null, null, null, null, null);
        resultsTo.setEvent(TEST_STRING_EVENT);
        resultsTo.setMaps(TEST_MATCH_MAPS);
        resultsTo.setTime(TEST_MATCH_TIME);
        resultsTo.setTeam1(TEST_TEAM_TO_1);
        resultsTo.setTeam2(TEST_TEAM_TO_2);
        resultsTo.setMatchId(TEST_MATCH_ID.toString());
    }

    @Test
    @DisplayName("Should return correct ResultsTo event")
    protected void hltvApiResultsTo_getEventTest(){
        assertEquals(TEST_STRING_EVENT, resultsTo.getEvent());
    }

    @Test
    @DisplayName("Should return correct ResultsTo maps")
    protected void hltvApiResultsTo_getMapsTest(){
        assertEquals(TEST_MATCH_MAPS, resultsTo.getMaps());
    }

    @Test
    @DisplayName("Should return correct ResultsTo time")
    protected void hltvApiResultsTo_getTimeTest(){
        assertEquals(TEST_MATCH_TIME, resultsTo.getTime());
    }

    @Test
    @DisplayName("Should return correct ResultsTo team1")
    protected void hltvApiResultsTo_getTeam1Test(){
        assertEquals(TEST_TEAM_TO_1, resultsTo.getTeam1());
    }

    @Test
    @DisplayName("Should return correct ResultsTo team2")
    protected void hltvApiResultsTo_getTeam2Test(){
        assertEquals(TEST_TEAM_TO_2, resultsTo.getTeam2());
    }

    @Test
    @DisplayName("Should return correct ResultsTo matchId")
    protected void hltvApiResultsTo_getMatchIdTest(){
        assertEquals(TEST_MATCH_ID.toString(), resultsTo.getMatchId());
    }

    @Test
    @DisplayName("Should return toString value")
    public void hltvApiMatchTo_toStringTest(){
        assertEquals(resultsTo.toString(), resultsTo.toString());
    }
}
