package com.valoler.telegram_hltv_bot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HltvApiMapsTest {

    HltvApiMaps maps;

    @BeforeEach
    protected void setUp(){
        //maps = new HltvApiMaps("testCode", "testName");
    }

    @Test
    public void hltvApiMaps_getCodeTest(){
        assertEquals("-", HltvApiMaps.CONFLICT.getCode());
    }

    @Test
    public void hltvApiMaps_getNameTest(){
        assertEquals("Conflict situation", HltvApiMaps.CONFLICT.getName());
    }
}
