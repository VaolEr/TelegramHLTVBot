package com.valoler.telegram_hltv_bot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.valoler.telegram_hltv_bot.TestData.TEST_EVENT_CREST;
import static com.valoler.telegram_hltv_bot.TestData.TEST_EVENT_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event event;

    @BeforeEach
    protected void setUp(){
        event = new Event();
        event.setName(TEST_EVENT_NAME);
        event.setCrest(TEST_EVENT_CREST);
    }

    @Test
    @DisplayName("Should return correct Event name")
    public void hltvApiEventTo_getNameTest(){
        assertEquals(TEST_EVENT_NAME, event.getName());
    }

    @Test
    @DisplayName("Should return correct Event crest")
    public void hltvApiEventTo_getCrestTest(){
        assertEquals(TEST_EVENT_CREST, event.getCrest());
    }

    @Test
    @DisplayName("Should return toString value")
    public void hltvApiEventTo_toStringTest(){
        assertEquals(event.toString(), event.toString());
    }
}
