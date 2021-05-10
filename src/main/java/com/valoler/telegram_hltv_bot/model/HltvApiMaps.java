package com.valoler.telegram_hltv_bot.model;

public enum HltvApiMaps {

    ANCIENT("anc", "Ancient"),          //
    BO5("bo5", "Best of 5"),            //
    BO3("bo3", "Best of 3"),            //
    CACHE("","Cache"),
    SEASON("","Season"),
    DUST2("d2","Dust2"),                //
    MIRAGE("mrg","Mirage"),             //
    INFERNO("inf","Inferno"),           //
    NUKE("nuke","Nuke"),                //
    TRAIN("trn","Train"),               //
    COBBLESTONE("cbb","Cobblestone"),
    OVERPASS("ovp","Overpass"),         //
    TUSCAN("tsc","Tuscan"),
    VERTIGO("vtg","Vertigo"),           //
    CONFLICT("-", "Conflict situation") //
    ;

    private final String code;
    private final String name;

    HltvApiMaps(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
