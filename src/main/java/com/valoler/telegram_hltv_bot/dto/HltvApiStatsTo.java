package com.valoler.telegram_hltv_bot.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class HltvApiStatsTo {

    private HltvApiPlayerTo[] hltvApiPlayerTos;

    @Override
    public String toString() {
        return "HltvApiStatsTo{" +
                "hltvApiPlayerTos=" + Arrays.toString(hltvApiPlayerTos) +
                '}';
    }
}
