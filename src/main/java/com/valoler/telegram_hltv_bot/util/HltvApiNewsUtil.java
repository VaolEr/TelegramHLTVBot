package com.valoler.telegram_hltv_bot.util;

import com.valoler.telegram_hltv_bot.dto.HltvApiNewsTo;
import com.valoler.telegram_hltv_bot.model.HltvApiNews;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Util class for map News dto objects to News model objects
 * for Unofficial HLTV API
 *
 * @author ValolEr
 * @link https://github.com/dajk/hltv-api
 */

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HltvApiNewsUtil {

    public static HltvApiNewsTo toHltvApiNewsTo(HltvApiNews news) {
        return HltvApiNewsTo.builder()
                .title(news.getTitle())
                .description(news.getDescription())
                .link(news.getLink().toString())
                .time(news.getTime())
                .build();
    }

    public static List<HltvApiNewsTo> toHltvApiNewsTos(List<HltvApiNews> news) {
        return news.stream().map(HltvApiNewsUtil::toHltvApiNewsTo).collect(Collectors.toList());
    }

    public static HltvApiNews fromHltvApiNewsTo(HltvApiNewsTo newsTo) {
        HltvApiNews news = new HltvApiNews();
        news.setTitle(newsTo.getTitle());
        news.setDescription(newsTo.getDescription());
        //TODO maybe there is better way to catch this exception???
        try {
            news.setLink(new URL(newsTo.getLink()));
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
        }
        news.setTime(newsTo.getTime());
        return news;
    }

    public static List<HltvApiNews> fromHltvApiNewsTos(List<HltvApiNewsTo> newsTos) {
        return newsTos.stream().map(HltvApiNewsUtil::fromHltvApiNewsTo).collect(Collectors.toList());
    }

}
