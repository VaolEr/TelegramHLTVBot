package com.valoler.telegram_hltv_bot.model;

import com.valoler.telegram_hltv_bot.dto.HltvApiTeamTo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Results {

    String event;

    String maps;

    String time;

    HltvApiTeamTo hltvApiTeamTo1;

    HltvApiTeamTo hltvApiTeamTo2;

    String matchId;

}
