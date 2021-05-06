package com.valoler.telegram_hltv_bot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HltvApiEventTo {
    String name;
    String crest;

    @Override
    public String toString() {
        return "HltvApiEventTo{" +
                "name='" + name + '\'' +
                ", crest='" + crest + '\'' +
                '}';
    }
}
