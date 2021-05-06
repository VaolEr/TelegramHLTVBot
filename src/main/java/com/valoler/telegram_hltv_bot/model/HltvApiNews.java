package com.valoler.telegram_hltv_bot.model;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;

/**
 * This class describes model of News object
 * for Unofficial HLTV API
 * @link https://github.com/dajk/hltv-api
 *
 * @author ValolEr
 */
@Getter
@Setter
public class HltvApiNews {

    String title;
    String description;
    URL link;
    String time;

}
