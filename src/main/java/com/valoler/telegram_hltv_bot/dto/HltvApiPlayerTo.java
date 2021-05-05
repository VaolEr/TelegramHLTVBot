package com.valoler.telegram_hltv_bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HltvApiPlayerTo {

    //@JsonProperty("playerName")
    String playerName;
    //@JsonProperty("playerId")
    String playerId;
    //@JsonProperty("kills")
    Integer kills;
    //@JsonProperty("deaths")
    Integer deaths;
    //@JsonProperty("plusMinus")
    Integer plusMinus;
    //@JsonProperty("adr")
    Double adr;
    //@JsonProperty("kast")
    Double kast;
    //@JsonProperty("rating")
    Double rating;

    @Override
    public String toString() {
        return "HltvApiPlayerTo{" +
                "playerName='" + playerName + '\'' +
                ", playerId='" + playerId + '\'' +
                ", kills=" + kills +
                ", deaths=" + deaths +
                ", plusMinus=" + plusMinus +
                ", adr=" + adr +
                ", kast=" + kast +
                ", rating=" + rating +
                '}';
    }
}
