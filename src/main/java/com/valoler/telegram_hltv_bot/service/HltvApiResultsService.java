package com.valoler.telegram_hltv_bot.service;

import com.valoler.telegram_hltv_bot.dto.HltvApiResultsTo;
import com.valoler.telegram_hltv_bot.model.HltvApiMaps;
import com.valoler.telegram_hltv_bot.model.HltvApiNews;
import com.valoler.telegram_hltv_bot.model.HltvApiResults;
import com.valoler.telegram_hltv_bot.model.HltvApiTeams;
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

import static com.valoler.telegram_hltv_bot.util.HltvApiResultsUtil.fromHltvApiResultsTo;
import static com.valoler.telegram_hltv_bot.util.HltvApiResultsUtil.fromHltvApiResultsTos;

@Slf4j
@Service
@RequiredArgsConstructor
public class HltvApiResultsService {

    private final RestTemplate restTemplate;
    private final LocaleMessageService localeMessageService;

    @Value("${app.hltv.api.url}")
    private String EXTERNAL_REST_URL;

    @Value("${app.hltv.api.results}")
    private String API_LINK_URL;

    public @ResponseBody
    List<HltvApiResults> getResults() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        log.debug(EXTERNAL_REST_URL + API_LINK_URL);

        ResponseEntity<HltvApiResultsTo[]> response = restTemplate.exchange(
                EXTERNAL_REST_URL + API_LINK_URL,
                HttpMethod.GET,
                entity,
                HltvApiResultsTo[].class
        );
        HltvApiResultsTo[] list = response.getBody();

        assert list != null;
        for (HltvApiResultsTo r : list) {
            log.info(r.toString());
        }

        return fromHltvApiResultsTos(Arrays.asList(list));
    }


    public SendMessage prepareResultsMessage(Message message, HltvApiResults results) {

            // Create new SendMessage
            SendMessage sendMessage = new SendMessage();
            // Set chatID to sendMessage
            sendMessage.setChatId(message.getChatId().toString());
            // Init new inline keyboard markup
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            // Create new inline keyboard button
            InlineKeyboardButton button_resultsUrl = new InlineKeyboardButton();

            button_resultsUrl.setText(localeMessageService.getMessage("inlineKeyboard.results.read"));
            button_resultsUrl.setUrl("https://www.hltv.org" + results.getMatchId());
            // Create new inline keyboard row for store buttons

            List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
            keyboardButtonsRow1.add(button_resultsUrl);
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

            Map<String, String> hltvApiMaps = new HashMap<>();
            for (HltvApiMaps map : HltvApiMaps.values()) {
                hltvApiMaps.put(map.getCode(), map.getName());
            }

            messageText.format("* %S *%n" +                          // Event name
                            "%n" +                                  // space
                            "Maps: %s%n" +                          // Maps: if bo1 - map name, else - bo2, bo3
                            "%n" +                                  // space
                            "[%S]() \\[*%d*] vs \\[*%d*] [%S]()%n" +  // Team 1 (logo) [score] vs [score] Team 2 (logo)
                            "%n" +                                  // space
                            "Time: %s",                             // Time, when game was played
                    results.getEvent(),
                    hltvApiMaps.get(results.getMaps()),
                    results.getTeam1().getName(), results.getTeam1().getResult(), results.getTeam2().getResult(), results.getTeam2().getName(),
                    results.getTime());

            //sendMessage1.setText("[ ](https://upload.wikimedia.org/wikipedia/commons/thumb/0/02/Stack_Overflow_logo.svg/200px-Stack_Overflow_logo.svg.png)" + news.getDescription()); //message with image
            sendMessage.setText(String.valueOf(messageText));

            return sendMessage;
    }

        public SendMessage prepareResultsMessageOrReturnEmptyMessage(Message message, HltvApiResults results, String callbackCommandName) {

            String commandName = HltvApiTeams.valueOf(callbackCommandName).getHltvApiName();

            // Create new SendMessage
            SendMessage sendMessage = new SendMessage();
            // Set chatID to sendMessage
            sendMessage.setChatId(message.getChatId().toString());

            if(results.getTeam1().getName().equalsIgnoreCase(commandName) || results.getTeam2().getName().equalsIgnoreCase(commandName)) {
                // Init new inline keyboard markup
                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                // Create new inline keyboard button
                InlineKeyboardButton button_resultsUrl = new InlineKeyboardButton();

                button_resultsUrl.setText(localeMessageService.getMessage("inlineKeyboard.results.read"));
                button_resultsUrl.setUrl("https://www.hltv.org" + results.getMatchId());
                // Create new inline keyboard row for store buttons

                List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
                keyboardButtonsRow1.add(button_resultsUrl);
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

                Map<String, String> hltvApiMaps = new HashMap<>();
                for (HltvApiMaps map : HltvApiMaps.values()) {
                    hltvApiMaps.put(map.getCode(), map.getName());
                }

                messageText.format("* %S *%n" +                          // Event name
                                "%n" +                                  // space
                                "Maps: %s%n" +                          // Maps: if bo1 - map name, else - bo2, bo3
                                "%n" +                                  // space
                                "[%S]() \\[*%d*] vs \\[*%d*] [%S]()%n" +  // Team 1 (logo) [score] vs [score] Team 2 (logo)
                                "%n" +                                  // space
                                "Time: %s",                             // Time, when game was played
                        results.getEvent(),
                        hltvApiMaps.get(results.getMaps()),
                        results.getTeam1().getName(), results.getTeam1().getResult(), results.getTeam2().getResult(), results.getTeam2().getName(),
                        results.getTime());

                sendMessage.setText(String.valueOf(messageText));

            } else {
                sendMessage.setText("app.sendMessage.empty");
            }
            return sendMessage;
        }
}
