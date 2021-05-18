package com.valoler.telegram_hltv_bot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.valoler.telegram_hltv_bot.TestData.*;
import static com.valoler.telegram_hltv_bot.TestData.TEST_NEWS_TIME;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HltvApiNewsTest {
    HltvApiNews news;

    URL link;
    @BeforeEach
    protected void setUp(){
        news = new HltvApiNews();
        news.setTitle(TEST_NEWS_TITLE);
        news.setDescription(TEST_NEWS_DESCRIPTION);
        try {
            link = new URL(TEST_NEWS_LINK);
            news.setLink(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        news.setTime(TEST_NEWS_TIME);
    }

    @Test
    @DisplayName("Should return correct News title")
    public void hltvApiNewsTo_getTitleTest(){
        assertEquals(TEST_NEWS_TITLE, news.getTitle());
    }

    @Test
    @DisplayName("Should return correct News description")
    public void hltvApiNewsTo_getDescriptionTest(){
        assertEquals(TEST_NEWS_DESCRIPTION, news.getDescription());
    }

    @Test
    @DisplayName("Should return correct News link")
    public void hltvApiNewsTo_getLinkTest(){
        assertEquals(link, news.getLink());
    }

    @Test
    @DisplayName("Should return correct News time")
    public void hltvApiNewsTo_getTimeTest(){
        assertEquals(TEST_NEWS_TIME, news.getTime());
    }

    @Test
    @DisplayName("Should return toString value")
    public void hltvApiNewsTo_toStringTest(){
        assertEquals(news.toString(), news.toString());
    }
}
