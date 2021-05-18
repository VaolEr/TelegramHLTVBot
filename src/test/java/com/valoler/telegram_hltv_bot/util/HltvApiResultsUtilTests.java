package com.valoler.telegram_hltv_bot.util;

import com.valoler.telegram_hltv_bot.dto.HltvApiResultsTo;
import com.valoler.telegram_hltv_bot.dto.HltvApiTeamTo;
import com.valoler.telegram_hltv_bot.model.HltvApiResults;
import com.valoler.telegram_hltv_bot.model.HltvApiTeam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.valoler.telegram_hltv_bot.TestData.TEST_EVENT_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author ValolEr
 *
 */
public class HltvApiResultsUtilTests {

    public static final String TEST_MAPS = "TestMaps";
    public static final String TEST_TIME = "TestTime";
    public static final String TEST_MATCHID = "TestMatchId";

    HltvApiResults testResults;
    HltvApiResultsTo testResultsTo;

    HltvApiTeam team1;
    HltvApiTeam team2;

    HltvApiTeamTo team1To;
    HltvApiTeamTo team2To;

    @BeforeEach
    public void setUp(){
        team1 = new HltvApiTeam(); //empty, because we have HltvApiTeamUtil tests
//        team1.setName("Team1");
//        team1.setCrest("link_Team1");
//        team1.setResult(5);

        team2 = new HltvApiTeam(); //empty, because we have HltvApiTeamUtil tests
//        team2.setName("Team2");
//        team2.setCrest("link_Team2");
//        team2.setResult(10);

        team1To = HltvApiTeamUtil.toHltvApiTeamTo(team1);
        team2To = HltvApiTeamUtil.toHltvApiTeamTo(team2);

        testResults = new HltvApiResults();

        testResults.setEvent(TEST_EVENT_NAME);
        testResults.setMaps(TEST_MAPS);
        testResults.setTeam1(team1);
        testResults.setTeam2(team2);
        testResults.setTime(TEST_TIME);
        testResults.setMatchId(TEST_MATCHID);

        testResultsTo = HltvApiResultsUtil.toHltvApiResultsTo(testResults);
    }

    @Test
    public void testToHltvApiResultsTo(){
        assertEquals(TEST_EVENT_NAME, testResultsTo.getEvent());
        assertEquals(TEST_MAPS, testResultsTo.getMaps());
        assertEquals(TEST_TIME, testResultsTo.getTime());
        assertEquals(testResults.getTeam1().getClass(), HltvApiTeamUtil.fromHltvApiTeamTo(testResultsTo.getTeam1()).getClass()); // Class comparison, because we have HltvApiTeamUtil tests
        assertEquals(testResults.getTeam2().getClass(), HltvApiTeamUtil.fromHltvApiTeamTo(testResultsTo.getTeam2()).getClass()); // Class comparison, because we have HltvApiTeamUtil tests
        assertEquals(TEST_MATCHID, testResultsTo.getMatchId());
    }

    @Test
    public void testToHltvApiResultsTos() {
        List<HltvApiResults> testResultsList = new ArrayList<>();
        testResultsList.add(testResults);

        List<HltvApiResultsTo> testResultsToList = HltvApiResultsUtil.toHltvApiResultsTos(testResultsList);

        assertEquals(testResultsList.get(0).getEvent(), testResultsToList.get(0).getEvent());
        assertEquals(testResultsList.get(0).getMaps(), testResultsToList.get(0).getMaps());
        assertEquals(testResultsList.get(0).getTeam1().getClass(), HltvApiTeamUtil.fromHltvApiTeamTo(testResultsToList.get(0).getTeam1()).getClass()); // Class comparison, because we have HltvApiTeamUtil tests
        assertEquals(testResultsList.get(0).getTeam2().getClass(), HltvApiTeamUtil.fromHltvApiTeamTo(testResultsToList.get(0).getTeam2()).getClass()); // Class comparison, because we have HltvApiTeamUtil tests
        assertEquals(testResultsList.get(0).getTime(), testResultsToList.get(0).getTime());
        assertEquals(testResultsList.get(0).getMatchId(), testResultsToList.get(0).getMatchId());

    }

    @Test
    public void testFromHltvApiResultsTo() {
        HltvApiResults results = HltvApiResultsUtil.fromHltvApiResultsTo(testResultsTo);

        assertEquals(TEST_EVENT_NAME, results.getEvent());
        assertEquals(TEST_MAPS, results.getMaps());
        assertEquals(TEST_TIME, results.getTime());
        assertEquals(TEST_MATCHID, results.getMatchId());
        assertEquals(team1.getClass(), results.getTeam1().getClass()); // Class comparison, because we have HltvApiTeamUtil tests
        assertEquals(team2.getClass(), results.getTeam2().getClass()); // Class comparison, because we have HltvApiTeamUtil tests
    }

    @Test
    public void testFromHltvApiResultsTos(){
        List<HltvApiResultsTo> testResultsToList = new ArrayList<>();
        testResultsToList.add(testResultsTo);

        List<HltvApiResults> testResultsList = HltvApiResultsUtil.fromHltvApiResultsTos(testResultsToList);

        assertEquals(testResultsToList.get(0).getEvent(), testResultsList.get(0).getEvent());
        assertEquals(testResultsToList.get(0).getMaps(), testResultsList.get(0).getMaps());
        assertEquals(HltvApiTeamUtil.fromHltvApiTeamTo(testResultsToList.get(0).getTeam1()).getClass(), testResultsList.get(0).getTeam1().getClass()); // Class comparison, because we have HltvApiTeamUtil tests
        assertEquals(HltvApiTeamUtil.fromHltvApiTeamTo(testResultsToList.get(0).getTeam2()).getClass(), testResultsList.get(0).getTeam2().getClass()); // Class comparison, because we have HltvApiTeamUtil tests
        assertEquals(testResultsToList.get(0).getTime(), testResultsList.get(0).getTime());
        assertEquals(testResultsToList.get(0).getMatchId(), testResultsList.get(0).getMatchId());
    }
}
