package com.valoler.telegram_hltv_bot.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.valoler.telegram_hltv_bot.TestData.TEST_EVENT_CREST;
import static com.valoler.telegram_hltv_bot.TestData.TEST_EVENT_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HltvApiEventToTest {

    HltvApiEventTo eventTo;



    @BeforeEach
    protected void setUp(){
        eventTo = new HltvApiEventTo();
        eventTo.setName(TEST_EVENT_NAME);
        eventTo.setCrest(TEST_EVENT_CREST);
    }

    @Test
    @DisplayName("Should return correct EventTo name")
    public void hltvApiEventTo_getName(){
        assertEquals(TEST_EVENT_NAME, eventTo.getName());
    }

    @Test
    @DisplayName("Should return correct EventTo crest")
    public void hltvApiEventTo_getCrest(){
        assertEquals(TEST_EVENT_CREST, eventTo.getCrest());
    }
}
