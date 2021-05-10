package com.valoler.telegram_hltv_bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class describes DTO object
 * for Results from HLTV API (https://github.com/dajk/hltv-api)
 */
@Builder
@Getter
@Setter
public class HltvApiResultsTo {

    private String event;
    private String maps;
    private String time;
    @JsonProperty("team1")
    private HltvApiTeamTo team1;
    @JsonProperty("team2")
    private HltvApiTeamTo team2;
    private String matchId;

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
