package com.valoler.telegram_hltv_bot.controller;

import com.valoler.telegram_hltv_bot.botapi.TelegramHLTVBotTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BotWebHookControllerTest {

    @Value("${app.endpoints.base_path}" + "${app.endpoints.telegram-bots.base_url}")
    String path;

    @Autowired
    protected MockMvc mockMvc;


    TelegramHLTVBotTest telegramHLTVBot = Mockito.mock(TelegramHLTVBotTest.class);

    protected HttpHeaders headers;

    @Autowired
    private BotWebHookController controller;

    Update update;
    final String testChatID = "123456";
    Message message;
    Chat chat;

    @BeforeEach
    protected void setUp() {
        message = new Message();
        message.setMessageId(12345);
        message.setText("TestText");

        chat = new Chat();
        chat.setId(12346L);

        message.setChat(chat);

        update = new Update();
        update.setUpdateId(1234567);
        update.setMessage(message);

        headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(APPLICATION_JSON));
        headers.setContentType(APPLICATION_JSON);

    }

    @Test
    @DisplayName("Check that context is creating BotWebHookController")
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void onUpdateReceivedTest() throws Exception{

        //TODO write correct test. Read more info for it.
        SendMessage testMessage = new SendMessage(testChatID, "TestMessage");

        //when(telegramHLTVBot.onWebhookUpdateReceived(update)).thenReturn("Ok");

        this.mockMvc.perform(
                post(path+"/telegram-HLTV-bot")
                        .contentType(APPLICATION_JSON)
                        .content("{\"quote\":\"Hasta la vista, baby\", \"movie\": \"Terminator\"}"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
