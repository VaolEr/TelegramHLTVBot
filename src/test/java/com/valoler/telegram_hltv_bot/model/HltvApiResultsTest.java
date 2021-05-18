package com.valoler.telegram_hltv_bot.model;

import com.valoler.telegram_hltv_bot.util.HltvApiTeamUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.valoler.telegram_hltv_bot.TestData.*;
import static com.valoler.telegram_hltv_bot.TestData.TEST_MATCH_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HltvApiResultsTest {
    HltvApiResults results;

    HltvApiTeam team1;
    HltvApiTeam team2;

    @BeforeEach
    protected void setUp(){
        results = new HltvApiResults();
        results.setEvent(TEST_STRING_EVENT);
        results.setMaps(TEST_MATCH_MAPS);
        results.setTime(TEST_MATCH_TIME);
        team1 = HltvApiTeamUtil.fromHltvApiTeamTo(TEST_TEAM_TO_1);
        team2 = HltvApiTeamUtil.fromHltvApiTeamTo(TEST_TEAM_TO_2);
        results.setTeam1(team1);
        results.setTeam2(team2);
        results.setMatchId(TEST_MATCH_ID.toString());
    }

    @Test
    @DisplayName("Should return correct Results event")
    protected void hltvApiResultsTo_getEventTest(){
        assertEquals(TEST_STRING_EVENT, results.getEvent());
    }

    @Test
    @DisplayName("Should return correct Results maps")
    protected void hltvApiResultsTo_getMapsTest(){
        assertEquals(TEST_MATCH_MAPS, results.getMaps());
    }

    @Test
    @DisplayName("Should return correct Results time")
    protected void hltvApiResultsTo_getTimeTest(){
        assertEquals(TEST_MATCH_TIME, results.getTime());
    }

    @Test
    @DisplayName("Should return correct Results team1")
    protected void hltvApiResultsTo_getTeam1Test(){
        assertEquals(team1, results.getTeam1());
    }

    @Test
    @DisplayName("Should return correct Results team2")
    protected void hltvApiResultsTo_getTeam2Test(){
        assertEquals(team2, results.getTeam2());
    }

    @Test
    @DisplayName("Should return correct Results matchId")
    protected void hltvApiResultsTo_getMatchIdTest(){
        assertEquals(TEST_MATCH_ID.toString(), results.getMatchId());
    }

    @Test
    @DisplayName("Should return toString value")
    public void hltvApiMatchTo_toStringTest(){
        assertEquals(results.toString(), results.toString());
    }
}
