package com.valoler.telegram_hltv_bot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HltvApiTeamsTest {

    @BeforeEach
    protected void setUp(){

    }

    @Test
    protected void getCallbackRequestNameTest(){
        assertEquals("NAVI", HltvApiTeams.NAVI.getCallbackRequestName());
    }

    @Test
    protected void getHltvApiNameTest(){
        assertEquals("Natus Vincere", HltvApiTeams.NAVI.getHltvApiName());
    }
}
