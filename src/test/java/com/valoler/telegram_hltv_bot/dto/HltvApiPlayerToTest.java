package com.valoler.telegram_hltv_bot.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.valoler.telegram_hltv_bot.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HltvApiPlayerToTest {

    HltvApiPlayerTo playerTo;

    @BeforeEach
    protected void setUp(){
        playerTo = new HltvApiPlayerTo();
        playerTo.setPlayerId(TEST_PLAYER_ID);
        playerTo.setPlayerName(TEST_PLAYER_NAME);
        playerTo.setKills(TEST_PLAYER_KILLS);
        playerTo.setDeaths(TEST_PLAYER_DEATHS);
        playerTo.setPlusMinus(TEST_PLAYER_PLUSMINUS);
        playerTo.setAdr(TEST_PLAYER_ADR);
        playerTo.setKast(TEST_PLAYER_KAST);
        playerTo.setRating(TEST_PLAYER_RATING);
    }

    @Test
    @DisplayName("Should return correct PlayerTo playerId")
    public void hltvApiEventTo_getPlayerIdTest(){
        assertEquals(TEST_PLAYER_ID, playerTo.getPlayerId());
    }

    @Test
    @DisplayName("Should return correct PlayerTo playerName")
    public void hltvApiEventTo_getPlayerNameTest(){
        assertEquals(TEST_PLAYER_NAME, playerTo.getPlayerName());
    }

    @Test
    @DisplayName("Should return correct PlayerTo kills")
    public void hltvApiEventTo_getKillsTest(){
        assertEquals(TEST_PLAYER_KILLS, playerTo.getKills());
    }

    @Test
    @DisplayName("Should return correct PlayerTo deaths")
    public void hltvApiEventTo_getDeathsTest(){
        assertEquals(TEST_PLAYER_DEATHS, playerTo.getDeaths());
    }

    @Test
    @DisplayName("Should return correct PlayerTo plusMinus")
    public void hltvApiEventTo_getPlusMinusTest(){
        assertEquals(TEST_PLAYER_PLUSMINUS, playerTo.getPlusMinus());
    }

    @Test
    @DisplayName("Should return correct PlayerTo adr")
    public void hltvApiEventTo_getAdrTest(){
        assertEquals(TEST_PLAYER_ADR, playerTo.getAdr());
    }

    @Test
    @DisplayName("Should return correct PlayerTo kast")
    public void hltvApiEventTo_getKastTest(){
        assertEquals(TEST_PLAYER_KAST, playerTo.getKast());
    }

    @Test
    @DisplayName("Should return correct PlayerTo rating")
    public void hltvApiEventTo_getRatingTest(){
        assertEquals(TEST_PLAYER_RATING, playerTo.getRating());
    }

    @Test
    @DisplayName("Should return toString value")
    public void hltvApiEventTo_toStringTest(){
        assertEquals(playerTo.toString(), playerTo.toString());
    }
}
