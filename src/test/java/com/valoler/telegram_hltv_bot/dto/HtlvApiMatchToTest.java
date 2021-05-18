package com.valoler.telegram_hltv_bot.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.valoler.telegram_hltv_bot.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HtlvApiMatchToTest {

    HltvApiMatchTo hltvApiMatchTo;

    HltvApiTeamTo teamTo1;
    HltvApiTeamTo teamTo2;
    HltvApiTeamTo[] teams;
    @BeforeEach
    protected void setUp(){
        hltvApiMatchTo = new HltvApiMatchTo();

        hltvApiMatchTo.setId(TEST_MATCH_ID);
        hltvApiMatchTo.setLink(TEST_MATCH_LINK);
        hltvApiMatchTo.setTime(TEST_MATCH_TIME);
        hltvApiMatchTo.setEvent(TEST_MATCH_EVENT);
        hltvApiMatchTo.setStars(TEST_MATCH_STARS);
        hltvApiMatchTo.setMap(TEST_MATCH_MAPS);

        teamTo1 = new HltvApiTeamTo(null, null, null);
        teamTo1.setName(TEST_TEAM_NAME);
        teamTo1.setCrest(TEST_TEAM_CREST);
        teamTo1.setResult(TEST_TEAM_RESULT);

        teamTo2 = new HltvApiTeamTo(null, null, null);
        teamTo2.setName(TEST_TEAM_NAME + "2");
        teamTo2.setCrest(TEST_TEAM_CREST + "2");
        teamTo2.setResult(TEST_TEAM_RESULT-5);

        teams = new HltvApiTeamTo[2];
        teams[0] = teamTo1;
        teams[1] = teamTo2;
        hltvApiMatchTo.setTeams(teams);
    }

    @Test
    @DisplayName("Should return correct MatchTo id")
    protected void hltvApiMatchTo_getIdTest(){
        assertEquals(TEST_MATCH_ID, hltvApiMatchTo.getId());
    }

    @Test
    @DisplayName("Should return correct MatchTo link")
    protected void hltvApiMatchTo_getLinkTest(){
        assertEquals(TEST_MATCH_LINK, hltvApiMatchTo.getLink());
    }

    @Test
    @DisplayName("Should return correct MatchTo time")
    protected void hltvApiMatchTo_getTimeTest(){
        assertEquals(TEST_MATCH_TIME, hltvApiMatchTo.getTime());
    }

    @Test
    @DisplayName("Should return correct MatchTo event")
    protected void hltvApiMatchTo_getEventTest(){
        assertEquals(TEST_MATCH_EVENT, hltvApiMatchTo.getEvent());
    }

    @Test
    @DisplayName("Should return correct MatchTo stars")
    protected void hltvApiMatchTo_getStarsTest(){
        assertEquals(TEST_MATCH_STARS, hltvApiMatchTo.getStars());
    }

    @Test
    @DisplayName("Should return correct MatchTo maps")
    protected void hltvApiMatchTo_getMapsTest(){
        assertEquals(TEST_MATCH_MAPS, hltvApiMatchTo.getMap());
    }

    @Test
    @DisplayName("Should return correct MatchTo teams array")
    protected void hltvApiMatchTo_getTeamsTest(){
        assertEquals(teams, hltvApiMatchTo.getTeams());
    }

}
