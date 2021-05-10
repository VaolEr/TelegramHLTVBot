package com.valoler.telegram_hltv_bot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HltvApiResults {

    private String event;
    private String maps;
    private String time;
    private HltvApiTeam team1;
    private HltvApiTeam team2;
    private String matchId;

    @Override
    public String toString() {
        return "HltvApiResults{" +
                "event='" + event + '\'' +
                ", maps='" + maps + '\'' +
                ", time='" + time + '\'' +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", matchId='" + matchId + '\'' +
                '}';
    }
}
