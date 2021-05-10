package com.valoler.telegram_hltv_bot.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HltvApiTeam {

    private String name;

    private String crest;

    private Integer result;

    @Override
    public String toString() {
        return "HltvApiTeam{" +
                "name='" + name + '\'' +
                ", crest='" + crest + '\'' +
                ", result=" + result +
                '}';
    }

}
