package com.valoler.telegram_hltv_bot.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.valoler.telegram_hltv_bot.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HltvApiNewsToTest {

    HltvApiNewsTo hltvApiNewsTo;

    @BeforeEach
    protected void setUp(){
        hltvApiNewsTo = new HltvApiNewsTo(null, null,null, null);
        hltvApiNewsTo.setTitle(TEST_NEWS_TITLE);
        hltvApiNewsTo.setDescription(TEST_NEWS_DESCRIPTION);
        hltvApiNewsTo.setLink(TEST_NEWS_LINK);
        hltvApiNewsTo.setTime(TEST_NEWS_TIME);
    }

    @Test
    @DisplayName("Should return correct NewsTo title")
    public void hltvApiNewsTo_getTitleTest(){
        assertEquals(TEST_NEWS_TITLE, hltvApiNewsTo.getTitle());
    }

    @Test
    @DisplayName("Should return correct NewsTo description")
    public void hltvApiNewsTo_getDescriptionTest(){
        assertEquals(TEST_NEWS_DESCRIPTION, hltvApiNewsTo.getDescription());
    }

    @Test
    @DisplayName("Should return correct NewsTo link")
    public void hltvApiNewsTo_getLinkTest(){
        assertEquals(TEST_NEWS_LINK, hltvApiNewsTo.getLink());
    }

    @Test
    @DisplayName("Should return correct NewsTo time")
    public void hltvApiNewsTo_getTimeTest(){
        assertEquals(TEST_NEWS_TIME, hltvApiNewsTo.getTime());
    }

    @Test
    @DisplayName("Should return toString value")
    public void hltvApiNewsTo_toStringTest(){
        assertEquals(hltvApiNewsTo.toString(), hltvApiNewsTo.toString());
    }
}
