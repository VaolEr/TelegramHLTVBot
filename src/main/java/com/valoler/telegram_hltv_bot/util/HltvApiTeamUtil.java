package com.valoler.telegram_hltv_bot.util;

import com.valoler.telegram_hltv_bot.dto.HltvApiTeamTo;
import com.valoler.telegram_hltv_bot.model.HltvApiTeam;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Util class for map Team dto objects to Team model objects
 * for Unofficial HLTV API
 *
 * @author ValolEr
 * @link https://github.com/dajk/hltv-api
 */

public class HltvApiTeamUtil {

    public static HltvApiTeamTo toHltvApiTeamTo(HltvApiTeam team){
        return HltvApiTeamTo.builder()
                .name(team.getName())
                .crest(team.getCrest())
                .result(team.getResult())
                .build();
    }

    public static List<HltvApiTeamTo> toHltvApiTeamTos(List<HltvApiTeam> teams){
        return teams.stream().map(HltvApiTeamUtil::toHltvApiTeamTo).collect(Collectors.toList());
    }

    public static HltvApiTeam fromHltvApiTeamTo(HltvApiTeamTo teamTo){
        HltvApiTeam team = new HltvApiTeam();
        team.setName(teamTo.getName());
        team.setCrest(teamTo.getCrest());
        team.setResult(teamTo.getResult());
        return team;
    }

    public static List<HltvApiTeam> fromHltvApiTeamTos(List<HltvApiTeamTo> teamTos){
        return teamTos.stream().map(HltvApiTeamUtil::fromHltvApiTeamTo).collect(Collectors.toList());
    }
}
