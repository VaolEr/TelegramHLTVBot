package com.valoler.telegram_hltv_bot.service;

import com.valoler.telegram_hltv_bot.dto.HltvApiPlayerTo;
import com.valoler.telegram_hltv_bot.dto.HltvApiStatsTo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HltvApiStatsbyIdService {

    private final RestTemplate restTemplate;

    @Value("${app.hltv.api.url}")
    private String EXTERNAL_REST_URL;

    //TODO insert link from user's message!
    private String API_LINK_URL = "/matches/2332210/liquid-vs-faze-blast-pro-series-miami-2019";

    public @ResponseBody
    List<HltvApiPlayerTo> getStats() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        log.debug(EXTERNAL_REST_URL + API_LINK_URL);

        ResponseEntity<HltvApiPlayerTo[]> response = restTemplate.exchange(
                EXTERNAL_REST_URL + API_LINK_URL,
                HttpMethod.GET,
                entity,
                HltvApiPlayerTo[].class
        );
        if(response.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR))
        {
            //TODO create correct exception handler!
            log.info("Internal server error occurred. API side  error.");
            //TODO maybe return default set of players???
            return null;
        }
        HltvApiPlayerTo[] list = response.getBody();
        assert list != null;
        for(HltvApiPlayerTo r : list){
            log.info(r.toString());
        }
        return Arrays.asList(response.getBody());
    }


}
