package com.valoler.telegram_hltv_bot.util;

import com.valoler.telegram_hltv_bot.dto.HltvApiNewsTo;
import com.valoler.telegram_hltv_bot.model.HltvApiNews;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author ValolEr
 *
 */
public class HltvApiNewsUtilTests {

    static final String TEST_NEWS_TITLE = "TestTitle";
    static final String TEST_NEWS_DESCRIPTION = "TestDescription";
    URL TEST_NEWS_URL_LINK;
    static final String TEST_NEWS_TIME = "TestTime";

    HltvApiNewsTo testNewsTo;
    HltvApiNews testNews;

    @BeforeEach
    public void setUp(){

        try {
            TEST_NEWS_URL_LINK = new URL("http://some.host.com");
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        testNews = new HltvApiNews();
        testNews.setTitle(TEST_NEWS_TITLE);
        testNews.setDescription(TEST_NEWS_DESCRIPTION);
        testNews.setLink(TEST_NEWS_URL_LINK);
        testNews.setTime(TEST_NEWS_TIME);

        testNewsTo = HltvApiNewsUtil.toHltvApiNewsTo(testNews);
    }

    @Test
    public void testToHltvApiNewsTo() {
        assertEquals(TEST_NEWS_TITLE, testNewsTo.getTitle());
        assertEquals(TEST_NEWS_DESCRIPTION, testNewsTo.getDescription());
        try {
            assertEquals(TEST_NEWS_URL_LINK, new URL(testNewsTo.getLink()));
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        assertEquals(TEST_NEWS_TIME, testNewsTo.getTime());
    }

    @Test
    public void testToHltvApiNewsTos() {
        List<HltvApiNews> testNewsList = new ArrayList<>();
        testNewsList.add(testNews);

        List<HltvApiNewsTo> testNewsToList = HltvApiNewsUtil.toHltvApiNewsTos(testNewsList);

        assertEquals(testNewsList.get(0).getTitle(), testNewsToList.get(0).getTitle());
        assertEquals(testNewsList.get(0).getDescription(), testNewsToList.get(0).getDescription());
        assertEquals(testNewsList.get(0).getLink().toString(), testNewsToList.get(0).getLink());
        assertEquals(testNewsList.get(0).getTime(), testNewsToList.get(0).getTime());

    }

    @Test
    public void testFromHltvApiNewsTo() {
        HltvApiNews news = HltvApiNewsUtil.fromHltvApiNewsTo(testNewsTo);
        assertEquals(TEST_NEWS_TITLE, news.getTitle());
        assertEquals(TEST_NEWS_DESCRIPTION, news.getDescription());
        assertEquals(TEST_NEWS_URL_LINK, news.getLink());
        assertEquals(TEST_NEWS_TIME, news.getTime());
    }

    @Test
    public void testFromHltvApiNewsTos(){
        List<HltvApiNewsTo> testNewsToList = new ArrayList<>();
        testNewsToList.add(testNewsTo);

        List<HltvApiNews> testNewsList = HltvApiNewsUtil.fromHltvApiNewsTos(testNewsToList);

        assertEquals(testNewsToList.get(0).getTitle(), testNewsList.get(0).getTitle());
        assertEquals(testNewsToList.get(0).getDescription(), testNewsList.get(0).getDescription());
        assertEquals(testNewsToList.get(0).getLink(), testNewsList.get(0).getLink().toString());
        assertEquals(testNewsToList.get(0).getTime(), testNewsList.get(0).getTime());
    }
}
