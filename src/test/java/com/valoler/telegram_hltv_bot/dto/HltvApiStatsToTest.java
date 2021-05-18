package com.valoler.telegram_hltv_bot.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.valoler.telegram_hltv_bot.TestData.TEST_HLTVAPI_PLAYER_TO;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HltvApiStatsToTest {

    HltvApiStatsTo statsTo;

    HltvApiPlayerTo[] hltvApiPlayerTos;

    @BeforeEach
    protected void setUp(){

        hltvApiPlayerTos = new HltvApiPlayerTo[2];
        hltvApiPlayerTos[0] = TEST_HLTVAPI_PLAYER_TO;
        hltvApiPlayerTos[1] = TEST_HLTVAPI_PLAYER_TO;

        statsTo = new HltvApiStatsTo();

        statsTo.setHltvApiPlayerTos(hltvApiPlayerTos);
    }

    @Test
    @DisplayName("Should return correct StatsTo HltvApiPlayerTo array")
    protected void hltvApiStatsTo_getHltvApiPlayerTosTest(){
        assertEquals(hltvApiPlayerTos, statsTo.getHltvApiPlayerTos());
    }

    @Test
    @DisplayName("Should return toString value")
    public void hltvApiStatsTo_toStringTest(){
        assertEquals(statsTo.toString(), statsTo.toString());
    }
}
