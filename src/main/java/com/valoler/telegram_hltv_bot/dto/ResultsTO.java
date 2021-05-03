package com.valoler.telegram_hltv_bot.dto;

import com.valoler.telegram_hltv_bot.model.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultsTO {

    String event;

    String maps;

    String time;

    Team team1;

    Team team2;

    String matchId;
}
