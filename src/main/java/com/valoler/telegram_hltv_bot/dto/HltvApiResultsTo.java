package com.valoler.telegram_hltv_bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * This class describes DTO object
 * for Results from HLTV API (https://github.com/dajk/hltv-api)
 */

@Getter
@Setter
public class HltvApiResultsTo {

    String event;

    String maps;

    String time;

    @JsonProperty("team1")
    HltvApiTeamTo team1;

    @JsonProperty("team2")
    HltvApiTeamTo team2;

    String matchId;

    @Override
    public String toString() {
        return "HltvApiResultsTo{" +
                "event='" + event + '\'' +
                ", maps='" + maps + '\'' +
                ", time='" + time + '\'' +
                ", team1=" + team1.toString() +
                ", team2=" + team2.toString() +
                ", matchId='" + matchId + '\'' +
                '}';
    }
}
