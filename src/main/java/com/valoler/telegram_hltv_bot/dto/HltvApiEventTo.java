package com.valoler.telegram_hltv_bot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HltvApiEventTo {
    private String name;
    private String crest;

    @Override
    public String toString() {
        return "HltvApiEventTo{" +
                "name='" + name + '\'' +
                ", crest='" + crest + '\'' +
                '}';
    }
}
