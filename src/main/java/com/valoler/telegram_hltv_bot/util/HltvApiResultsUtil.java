package com.valoler.telegram_hltv_bot.util;

import com.valoler.telegram_hltv_bot.dto.HltvApiResultsTo;
import com.valoler.telegram_hltv_bot.model.HltvApiResults;
import com.valoler.telegram_hltv_bot.model.HltvApiTeam;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static com.valoler.telegram_hltv_bot.util.HltvApiTeamUtil.toHltvApiTeamTo;

/**
 * Util class for map Results dto objects to Results model objects
 * for Unofficial HLTV API
 *
 * @author ValolEr
 * @link https://github.com/dajk/hltv-api
 */

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HltvApiResultsUtil {

    public static HltvApiResultsTo toHltvApiResultsTo(HltvApiResults results){
        return HltvApiResultsTo.builder()
                .event(results.getEvent())
                .maps(results.getMaps())
                .time(results.getTime())
                .team1(toHltvApiTeamTo(results.getTeam1()))
                .team2(toHltvApiTeamTo(results.getTeam2()))
                .matchId(results.getMatchId())
                .build();
    }

    public static List<HltvApiResultsTo> toHltvApiResultsTos(List<HltvApiResults> results){
        return results.stream().map(HltvApiResultsUtil::toHltvApiResultsTo).collect(Collectors.toList());
    }

    public static HltvApiResults fromHltvApiResultsTo(HltvApiResultsTo resultsTo){
        HltvApiResults results = new HltvApiResults();
        results.setEvent(resultsTo.getEvent());
        results.setMaps(resultsTo.getMaps());
        results.setTime(resultsTo.getTime());

        HltvApiTeam team1 = new HltvApiTeam();
        team1.setName(resultsTo.getTeam1().getName());
        team1.setCrest(resultsTo.getTeam1().getCrest());
        team1.setResult(resultsTo.getTeam1().getResult());

        HltvApiTeam team2 = new HltvApiTeam();
        team2.setName(resultsTo.getTeam2().getName());
        team2.setCrest(resultsTo.getTeam2().getCrest());
        team2.setResult(resultsTo.getTeam2().getResult());
        //TODO change to fromHltvApiTo() method?
        results.setTeam1(team1);
        results.setTeam2(team2);

        results.setMatchId(resultsTo.getMatchId());

        return results;
    }

    public static List<HltvApiResults> fromHltvApiResultsTos(List<HltvApiResultsTo> resultsTos){
        return resultsTos.stream().map(HltvApiResultsUtil::fromHltvApiResultsTo).collect(Collectors.toList());
    }
}
