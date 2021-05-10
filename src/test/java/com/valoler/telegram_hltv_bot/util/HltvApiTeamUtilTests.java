package com.valoler.telegram_hltv_bot.util;

import com.valoler.telegram_hltv_bot.dto.HltvApiTeamTo;
import com.valoler.telegram_hltv_bot.model.HltvApiTeam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author ValolEr
 *
 */
public class HltvApiTeamUtilTests {

    private static final String TEST_TEAM_NAME = "TestTeam";
    private static final Integer TEST_TEAM_RESULT = 10;
    private static final String TEST_TEAM_CREST = "TestTeamCrest";


    HltvApiTeam testTeam;
    HltvApiTeamTo testTeamTo;

    @BeforeEach
    public void setUp(){

        testTeam = new HltvApiTeam();
        testTeam.setName(TEST_TEAM_NAME);
        testTeam.setResult(TEST_TEAM_RESULT);
        testTeam.setCrest(TEST_TEAM_CREST);

        testTeamTo = HltvApiTeamUtil.toHltvApiTeamTo(testTeam);
    }

    @Test
    public void testToHltvApiTeamTo(){
        assertEquals(testTeam.getName(), testTeamTo.getName());
        assertEquals(testTeam.getResult(), testTeamTo.getResult());
        assertEquals(testTeam.getCrest(), testTeamTo.getCrest());
    }

    @Test
    public void testToHltvApiTeamTos(){

        List<HltvApiTeam> teamList = new ArrayList<>();
        teamList.add(testTeam);

        List<HltvApiTeamTo> teamTosList = HltvApiTeamUtil.toHltvApiTeamTos(teamList);

        assertEquals(teamList.get(0).getName(), teamTosList.get(0).getName());
        assertEquals(teamList.get(0).getCrest(), teamTosList.get(0).getCrest());
        assertEquals(teamList.get(0).getResult(), teamTosList.get(0).getResult());

    }

    @Test
    public void testFromHltvApiTeamTo(){
        HltvApiTeam team = HltvApiTeamUtil.fromHltvApiTeamTo(testTeamTo);

        assertEquals(TEST_TEAM_NAME, team.getName());
        assertEquals(TEST_TEAM_RESULT, team.getResult());
        assertEquals(TEST_TEAM_CREST, team.getCrest());
    }

    @Test
    public void testFromHltvApiTeamTos(){
        List<HltvApiTeamTo> teamTosList = new ArrayList<>();
        teamTosList.add(testTeamTo);

        List<HltvApiTeam> teamsList = HltvApiTeamUtil.fromHltvApiTeamTos(teamTosList);

        assertEquals(teamTosList.get(0).getName(), teamsList.get(0).getName());
        assertEquals(teamTosList.get(0).getCrest(), teamsList.get(0).getCrest());
        assertEquals(teamTosList.get(0).getResult(), teamsList.get(0).getResult());
    }

}
