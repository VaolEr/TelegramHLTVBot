package com.valoler.telegram_hltv_bot.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * This class describes DTO object
 * for Team from HLTV API (https://github.com/dajk/hltv-api)
 */

@Getter
@Setter
public class HltvApiTeamTo {

    String name;

    String crest;

    Integer result;

    @Override
    public String toString() {
        return "HltvApiTeamTo{" +
                "name='" + name + '\'' +
                ", crest='" + crest + '\'' +
                ", result=" + result +
                '}';
    }
}
