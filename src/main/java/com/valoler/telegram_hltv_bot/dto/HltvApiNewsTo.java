package com.valoler.telegram_hltv_bot.dto;

/**
 * This class describes DTO object
 * for News from HLTV API
 * @link https://github.com/dajk/hltv-api
 *
 * @author ValolEr
 */

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class HltvApiNewsTo {

    private String title;
    private String description;
    private String link;
    private String time;

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
