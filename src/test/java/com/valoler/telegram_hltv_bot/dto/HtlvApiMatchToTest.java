package com.valoler.telegram_hltv_bot.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.valoler.telegram_hltv_bot.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HtlvApiMatchToTest {

    HltvApiMatchTo hltvApiMatchTo;

    HltvApiTeamTo[] teams;
    @BeforeEach
    protected void setUp(){
        hltvApiMatchTo = new HltvApiMatchTo();

        hltvApiMatchTo.setId(TEST_MATCH_ID);
        hltvApiMatchTo.setLink(TEST_MATCH_LINK);
        hltvApiMatchTo.setTime(TEST_MATCH_TIME);
        hltvApiMatchTo.setEvent(TEST_EVENT);
        hltvApiMatchTo.setStars(TEST_MATCH_STARS);
        hltvApiMatchTo.setMap(TEST_MATCH_MAPS);

        teams = new HltvApiTeamTo[2];
        teams[0] = TEST_TEAM_TO_1;
        teams[1] = TEST_TEAM_TO_2;
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
        assertEquals(TEST_EVENT, hltvApiMatchTo.getEvent());
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

    @Test
    @DisplayName("Should return toString value")
    public void hltvApiMatchTo_toStringTest(){
        assertEquals(hltvApiMatchTo.toString(), hltvApiMatchTo.toString());
    }
}
