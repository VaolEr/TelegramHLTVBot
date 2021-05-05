package com.valoler.telegram_hltv_bot.util;

import com.valoler.telegram_hltv_bot.model.Results;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResultsUtil {

//    public static ResultsTo toResultsTo(Results results){
//        return ResultsTo.builder()
//                .event(results.getEvent())
//                .maps(results.getMaps())
//                .time(results.getTime())
//                .team1(results.getHltvApiTeamTO1())
//                .team2(results.getHltvApiTeamTO2())
//                .matchId(results.getMatchId())
//                .build();
//    }
//
//    public static List<ResultsTo> toResultsTos(List<Results> results){
//        return results.stream().map(ResultsUtil::toResultsTo).collect(Collectors.toList());
//    }
//
//    public static Results fromResultsTo(ResultsTo resultsTo){
//        Results newResults = new Results();
//
//        newResults.setEvent(resultsTo.getEvent());
//        newResults.setMaps(resultsTo.getMaps());
//        newResults.setTime(resultsTo.getTime());
//        newResults.setHltvApiTeamTO1(resultsTo.getTeam1());
//        newResults.setHltvApiTeamTO2(resultsTo.getTeam2());
//        newResults.setMatchId(resultsTo.getMatchId());
//
//        return newResults;
//    }
//
//    public static List<Results> fromResultsTos(List<ResultsTo> resultTos){
//        return resultTos.stream().map(ResultsUtil::fromResultsTo).collect(Collectors.toList());
//    }

}
