package com.valoler.telegram_hltv_bot.dto;

import com.valoler.telegram_hltv_bot.model.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class HltvApiMatchTo {
    Integer id;
    String link;
    String time;
    Event event;
    Integer stars;
    String map;
    HltvApiTeamTo[] teams;

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
