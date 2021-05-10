package com.valoler.telegram_hltv_bot.service;

import com.valoler.telegram_hltv_bot.dto.HltvApiNewsTo;
import com.valoler.telegram_hltv_bot.model.HltvApiNews;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.*;

import static com.valoler.telegram_hltv_bot.util.HltvApiNewsUtil.fromHltvApiNewsTo;
import static com.valoler.telegram_hltv_bot.util.HltvApiNewsUtil.fromHltvApiNewsTos;

/**
 * This controller is used to handle
 * data from JSON service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HltvApiNewsService {

    private final RestTemplate restTemplate;
    private final LocaleMessageService localeMessageService;

    @Value("${app.hltv.api.url}")
    private String EXTERNAL_REST_URL;

    @Value("${app.hltv.api.news}")
    private String API_LINK_URL;

    public @ResponseBody List<HltvApiNews> getNews() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        log.debug(EXTERNAL_REST_URL + API_LINK_URL);

        ResponseEntity<HltvApiNewsTo[]> response = restTemplate.exchange(
                EXTERNAL_REST_URL + API_LINK_URL,
                HttpMethod.GET,
                entity,
                HltvApiNewsTo[].class
        );

        HltvApiNewsTo[] newsTosList = response.getBody();

        assert newsTosList != null;
        for(HltvApiNewsTo r : newsTosList){
            log.info(r.toString());
        }

        return fromHltvApiNewsTos(Arrays.asList(newsTosList));
    }

    public SendMessage prepareNewsMessage(Message message, HltvApiNews news) {

        // Create new SendMessage
        SendMessage sendMessage = new SendMessage();
        // Set chatID to sendMessage
        sendMessage.setChatId(message.getChatId().toString());
        // Init new inline keyboard markup
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        // Create new inline keyboard button
        InlineKeyboardButton button_NewsUrl = new InlineKeyboardButton();
        button_NewsUrl.setText(localeMessageService.getMessage("inlineKeyboard.news.read"));
        button_NewsUrl.setUrl(news.getLink().toString());
        // Create new inline keyboard row for store buttons
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(button_NewsUrl);
        // Create new rows lLst for store keyboard's rows
        List<List<InlineKeyboardButton>> rowsList = new ArrayList<>();
        rowsList.add(keyboardButtonsRow1);
        // Set created keyboard to markup
        inlineKeyboardMarkup.setKeyboard(rowsList);
        // Set markup as message parametr
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        //Enable markdown formatting for format message text
        sendMessage.enableMarkdown(true);
        //Create template for all news and fill it from HltvApiNews objects
        Formatter messageText = new Formatter();
        messageText.format("* %S *%n%n%s%n", news.getTitle(), news.getDescription());

        //sendMessage1.setText("[ ](https://upload.wikimedia.org/wikipedia/commons/thumb/0/02/Stack_Overflow_logo.svg/200px-Stack_Overflow_logo.svg.png)" + news.getDescription()); //message with image
        sendMessage.setText(String.valueOf(messageText));

        return sendMessage;
    }
}
