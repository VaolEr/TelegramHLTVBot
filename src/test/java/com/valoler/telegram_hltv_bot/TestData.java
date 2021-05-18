package com.valoler.telegram_hltv_bot;

import com.valoler.telegram_hltv_bot.dto.HltvApiTeamTo;
import com.valoler.telegram_hltv_bot.model.Event;
import com.valoler.telegram_hltv_bot.model.HltvApiNews;
import com.valoler.telegram_hltv_bot.model.HltvApiResults;
import com.valoler.telegram_hltv_bot.model.HltvApiTeam;

import java.net.MalformedURLException;
import java.net.URL;

public class TestData {

    public static final String TEST_CHAT_ID = "123456";

    // Event test data block
    public static final String TEST_EVENT_NAME = "Test Event Name";
    public static final String TEST_EVENT_CREST = "Test Event Crest";

    // Match test data block
    public static final Integer TEST_MATCH_ID = 778;
    public static final String TEST_MATCH_LINK = "http:\\localhost:8080";
    public static final String TEST_MATCH_TIME = "yyyy-MMMM-d HH:mm:ss";
    public static final Event TEST_MATCH_EVENT;
    static {
        TEST_MATCH_EVENT = new Event();
        TEST_MATCH_EVENT.setName(TEST_EVENT_NAME);
        TEST_MATCH_EVENT.setCrest(TEST_EVENT_CREST);
    }
    public static final Integer TEST_MATCH_STARS = 1;
    public static final String TEST_MATCH_MAPS = "Test match maps";

    // Team test data block
    public static final String TEST_TEAM_NAME = "Test Team Name";
    public static final String TEST_TEAM_CREST = "Test Team Crest";
    public static final Integer TEST_TEAM_RESULT = 16;

    public static final HltvApiNews TEST_HLTVAPI_NEWS = new HltvApiNews();

    static {
        TEST_HLTVAPI_NEWS.setTitle("Test Title");
        try {
            TEST_HLTVAPI_NEWS.setLink(new URL("http:\\localhost:8080"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        TEST_HLTVAPI_NEWS.setTime("yyyy-MMMM-d HH:mm:ss");
        TEST_HLTVAPI_NEWS.setDescription("Test News Description");
    }

    public static final HltvApiResults TEST_HLTVAPI_RESULT = new HltvApiResults();

    static {
        TEST_HLTVAPI_RESULT.setMatchId("123456789");
        TEST_HLTVAPI_RESULT.setTeam1(new HltvApiTeam());
        TEST_HLTVAPI_RESULT.setTeam2(new HltvApiTeam());
        TEST_HLTVAPI_RESULT.setTime("yyyy-MMMM-d HH:mm:ss");
        TEST_HLTVAPI_RESULT.setEvent(TEST_EVENT_NAME);
        TEST_HLTVAPI_RESULT.setMaps("Test Maps");
    }

}
