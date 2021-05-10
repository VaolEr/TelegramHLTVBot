package com.valoler.telegram_hltv_bot.model;

public enum HltvApiTeams {
    NAVI("NAVI", "Natus Vincere"),
    GAMBIT("GAMBIT", "Gambit"),
    ASTRALIS("ASTRALIS", "Astralis"),
    G2("G2", "G2")
    ;

    private final String callbackRequestName;
    private final String hltvApiName;

    HltvApiTeams(String callbackRequestName, String hltvApiName) {
        this.callbackRequestName = callbackRequestName;
        this.hltvApiName = hltvApiName;
    }

    public String getCallbackRequestName() {
        return callbackRequestName;
    }

    public String getHltvApiName() {
        return hltvApiName;
    }
}
