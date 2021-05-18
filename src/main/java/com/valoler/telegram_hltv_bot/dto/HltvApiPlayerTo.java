package com.valoler.telegram_hltv_bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HltvApiPlayerTo {

    //@JsonProperty("playerName")
    private String playerName;
    //@JsonProperty("playerId")
    private String playerId;
    //@JsonProperty("kills")
    private Integer kills;
    //@JsonProperty("deaths")
    private Integer deaths;
    //@JsonProperty("plusMinus")
    private Integer plusMinus;
    //@JsonProperty("adr")
    private Double adr;
    //@JsonProperty("kast")
    private Double kast;
    //@JsonProperty("rating")
    private Double rating;

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
