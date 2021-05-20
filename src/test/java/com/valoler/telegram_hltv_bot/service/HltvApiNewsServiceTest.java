package com.valoler.telegram_hltv_bot.service;

import com.valoler.telegram_hltv_bot.dto.HltvApiMatchTo;
import com.valoler.telegram_hltv_bot.dto.HltvApiNewsTo;
import com.valoler.telegram_hltv_bot.model.HltvApiNews;
import com.valoler.telegram_hltv_bot.util.HltvApiNewsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static com.valoler.telegram_hltv_bot.TestData.TEST_CHAT_ID;
import static com.valoler.telegram_hltv_bot.TestData.TEST_HLTVAPI_NEWS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HltvApiNewsServiceTest {

    @Mock
    RestTemplate restTemplate;
    @Mock
    LocaleMessageService localeMessageService;

    @Mock
    Message message;

    @InjectMocks
    HltvApiNewsService newsService;


    URL url;
    String path = "/matches";

    HltvApiNews hltvApiNews;
    List<HltvApiNews> newsList;
    HltvApiNewsTo[] newsTos;

    @BeforeEach
    protected void setUp() throws MalformedURLException {
        url = new URL("https://hltv-api.vercel.app/api");

        hltvApiNews = TEST_HLTVAPI_NEWS;
        newsList = new ArrayList<>();
        newsList.add(hltvApiNews);
        newsTos = new HltvApiNewsTo[1];
        newsTos[0] = HltvApiNewsUtil.toHltvApiNewsTo(hltvApiNews);
    }

    @Test
    @DisplayName("Check that context is creating HltvApiNewsService")
    public void contextLoads() {
        assertThat(newsService).isNotNull();
    }

    @Test
    @DisplayName("Should return correct news list")
    public void hltvApiNewsService_getNewsTest(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<HltvApiNewsTo[]> response = new ResponseEntity<HltvApiNewsTo[]>(newsTos, HttpStatus.ACCEPTED);

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(HltvApiNewsTo[].class))).thenReturn(response);
        assertEquals(newsList.get(0).getTitle(), newsService.getNews().get(0).getTitle());
        assertEquals(newsList.get(0).getDescription(), newsService.getNews().get(0).getDescription());
        assertEquals(newsList.get(0).getLink(), newsService.getNews().get(0).getLink());
        assertEquals(newsList.get(0).getTime(), newsService.getNews().get(0).getTime());
    }

    @Test
    @DisplayName("Should return correct message")
    public void hltvApiNewsService_prepareNewsMessage(){

        SendMessage newsSendMessage = new SendMessage();
        newsSendMessage.setChatId(TEST_CHAT_ID);
        // Init new inline keyboard markup
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        // Create new inline keyboard button
        InlineKeyboardButton button_NewsUrl = new InlineKeyboardButton();
        button_NewsUrl.setText("Test button text");
        button_NewsUrl.setUrl(hltvApiNews.getLink().toString());
        // Create new inline keyboard row for store buttons
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(button_NewsUrl);
        // Create new rows lLst for store keyboard's rows
        List<List<InlineKeyboardButton>> rowsList = new ArrayList<>();
        rowsList.add(keyboardButtonsRow1);
        // Set created keyboard to markup
        inlineKeyboardMarkup.setKeyboard(rowsList);
        // Set markup as message parametr
        newsSendMessage.setReplyMarkup(inlineKeyboardMarkup);
        //Enable markdown formatting for format message text
        newsSendMessage.enableMarkdown(true);
        //Create template for all news and fill it from HltvApiNews objects
        Formatter messageText = new Formatter();
        messageText.format("* %S *%n%n%s%n", hltvApiNews.getTitle(), hltvApiNews.getDescription());

        newsSendMessage.setText(String.valueOf(messageText));
        //when
        when(message.getChatId()).thenReturn(123456L);
        when(localeMessageService.getMessage(anyString())).thenReturn("Test button text");

        //then
        assertEquals(newsSendMessage, newsService.prepareNewsMessage(message, hltvApiNews));

    }
}
