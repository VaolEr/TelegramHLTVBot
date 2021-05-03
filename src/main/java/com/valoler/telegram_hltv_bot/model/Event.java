package com.valoler.telegram_hltv_bot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
@NoArgsConstructor
public class Event {

    @JsonProperty
    String name;

    @JsonProperty
    URL crest;
}
