package com.valoler.telegram_hltv_bot.dto;

import lombok.*;

/**
 * This class describes DTO object
 * for Team from HLTV API (https://github.com/dajk/hltv-api)
 */

@Builder
@Getter
@Setter
public class HltvApiTeamTo {

    private String name;

    private String crest;

    private Integer result;

    @Override
    public String toString() {
        return "HltvApiTeamTo{" +
                "name='" + name + '\'' +
                ", crest='" + crest + '\'' +
                ", result=" + result +
                '}';
    }
}
