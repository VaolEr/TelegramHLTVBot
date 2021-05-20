package com.valoler.telegram_hltv_bot.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

@SpringBootTest
public class AbstractServiceTest {

    @Mock
    RestTemplate restTemplate;

    @Mock
    LocaleMessageService localeMessageService;

    @Mock
    Message message;

    URL url;

    HttpHeaders headers;
    HttpEntity<String> entity;
    @BeforeEach
    protected void setUp()throws MalformedURLException {

        url = new URL("https://hltv-api.vercel.app/api");

        headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0");
        entity = new HttpEntity<String>("parameters", headers);
    }
}
