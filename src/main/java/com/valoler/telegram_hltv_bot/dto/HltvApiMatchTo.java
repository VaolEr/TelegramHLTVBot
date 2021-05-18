package com.valoler.telegram_hltv_bot.dto;

import com.valoler.telegram_hltv_bot.model.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class HltvApiMatchTo {
    private Integer id;
    private String link;
    private String time;
    private Event event;
    private Integer stars;
    private String map;
    private HltvApiTeamTo[] teams;

    @Override
    public String toString() {
        return "HltvApiMatchTo{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", time='" + time + '\'' +
                ", event=" + event.toString() +
                ", stars=" + stars +
                ", map='" + map + '\'' +
                ", teams=" + Arrays.toString(teams) +
                '}';
    }
}
