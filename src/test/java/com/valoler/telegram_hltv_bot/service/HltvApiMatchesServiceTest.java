package com.valoler.telegram_hltv_bot.service;

import com.valoler.telegram_hltv_bot.dto.HltvApiMatchTo;
import com.valoler.telegram_hltv_bot.dto.HltvApiTeamTo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;

import static com.valoler.telegram_hltv_bot.TestData.*;
import static com.valoler.telegram_hltv_bot.TestData.TEST_TEAM_TO_2;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HltvApiMatchesServiceTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    HltvApiMatchesService matchesService;

    HltvApiMatchTo hltvApiMatchTo;
    HltvApiTeamTo[] teams;

    URL url;
    String path = "/matches";

    @BeforeEach
    protected void setUp() throws MalformedURLException {
        url = new URL("https://hltv-api.vercel.app/api");

        hltvApiMatchTo = new HltvApiMatchTo();

        hltvApiMatchTo.setId(TEST_MATCH_ID);
        hltvApiMatchTo.setLink(TEST_MATCH_LINK);
        hltvApiMatchTo.setTime(TEST_MATCH_TIME);
        hltvApiMatchTo.setEvent(TEST_EVENT);
        hltvApiMatchTo.setStars(TEST_MATCH_STARS);
        hltvApiMatchTo.setMap(TEST_MATCH_MAPS);

        teams = new HltvApiTeamTo[2];
        teams[0] = TEST_TEAM_TO_1;
        teams[1] = TEST_TEAM_TO_2;
        hltvApiMatchTo.setTeams(teams);
    }

    @Test
    @DisplayName("Check that context is creating HltvApiMatchesService")
    public void contextLoads() {
        assertThat(matchesService).isNotNull();
    }

    @Test
    @DisplayName("Should return matches as array of HltvApiMatchTo")
    public void getMatchesTest(){
        HltvApiMatchTo[] hltvApiMatchTos = new HltvApiMatchTo[1];
        hltvApiMatchTos[0] = hltvApiMatchTo;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<HltvApiMatchTo[]> response = new ResponseEntity<HltvApiMatchTo[]>(hltvApiMatchTos, HttpStatus.ACCEPTED);

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(HltvApiMatchTo[].class))).thenReturn(response);
        assertEquals(Arrays.asList(hltvApiMatchTos), matchesService.getMatches());

    }
}
