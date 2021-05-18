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
    public static final String TEST_STRING_EVENT = "Test String Event";
    public static final String TEST_EVENT_NAME = "Test Event Name";
    public static final String TEST_EVENT_CREST = "Test Event Crest";
    public static final Event TEST_EVENT;
    static {
        TEST_EVENT = new Event();
        TEST_EVENT.setName(TEST_EVENT_NAME);
        TEST_EVENT.setCrest(TEST_EVENT_CREST);
    }

    // Match test data block
    public static final Integer TEST_MATCH_ID = 778;
    public static final String TEST_MATCH_LINK = "http://test.matchLink.com";
    public static final String TEST_MATCH_TIME = "yyyy-MMMM-d HH:mm:ss";
    public static final Integer TEST_MATCH_STARS = 1;
    public static final String TEST_MATCH_MAPS = "Test match maps";

    // Team test data block
    public static final String TEST_TEAM_NAME = "Test Team Name";
    public static final String TEST_TEAM_CREST = "Test Team Crest";
    public static final Integer TEST_TEAM_RESULT = 16;
    public static final HltvApiTeamTo TEST_TEAM_TO_1;
    public static final HltvApiTeamTo TEST_TEAM_TO_2;
    static {
        TEST_TEAM_TO_1 = HltvApiTeamTo.builder()
                .name(TEST_TEAM_NAME)
                .crest(TEST_TEAM_CREST)
                .result(TEST_TEAM_RESULT)
                .build();
        TEST_TEAM_TO_1.setName(TEST_TEAM_NAME);
        TEST_TEAM_TO_1.setCrest(TEST_TEAM_CREST);
        TEST_TEAM_TO_1.setResult(TEST_TEAM_RESULT);

        TEST_TEAM_TO_2 = HltvApiTeamTo.builder()
                .name(TEST_TEAM_NAME)
                .crest(TEST_TEAM_CREST)
                .result(TEST_TEAM_RESULT)
                .build();
        TEST_TEAM_TO_2.setName(TEST_TEAM_NAME + "2");
        TEST_TEAM_TO_2.setCrest(TEST_TEAM_CREST + "2");
        TEST_TEAM_TO_2.setResult(TEST_TEAM_RESULT-5);
    }


    // News test data block
    public static final String TEST_NEWS_TITLE = "Test News Title";
    public static final String TEST_NEWS_DESCRIPTION = "Test News Description";
    public static final String TEST_NEWS_LINK = "http://test.newsLink.com";
    public static final String TEST_NEWS_TIME = "yyyy-MMMM-d HH:mm:ss";

    public static final HltvApiNews TEST_HLTVAPI_NEWS = new HltvApiNews();

    static {
        TEST_HLTVAPI_NEWS.setTitle(TEST_NEWS_TITLE);
        try {
            TEST_HLTVAPI_NEWS.setLink(new URL(TEST_NEWS_LINK));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        TEST_HLTVAPI_NEWS.setTime(TEST_NEWS_TIME);
        TEST_HLTVAPI_NEWS.setDescription(TEST_NEWS_DESCRIPTION);
    }

    // Player test data block
    public static final String TEST_PLAYER_NAME = "Test player name";
    public static final String TEST_PLAYER_ID = "Test player id";
    public static final Integer TEST_PLAYER_KILLS = 22;
    public static final Integer TEST_PLAYER_DEATHS = 11;
    public static final Integer TEST_PLAYER_PLUSMINUS = Math.abs(TEST_PLAYER_KILLS - TEST_PLAYER_DEATHS);
    public static final Double TEST_PLAYER_ADR = 157.3;
    public static final Double TEST_PLAYER_KAST = 1.17;
    public static final Double TEST_PLAYER_RATING = (double) (TEST_PLAYER_KILLS / TEST_PLAYER_DEATHS);


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
