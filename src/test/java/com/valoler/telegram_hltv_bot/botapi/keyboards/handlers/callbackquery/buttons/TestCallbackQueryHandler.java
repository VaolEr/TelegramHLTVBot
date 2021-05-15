package com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.buttons;

import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.BotCallbackQueryType;
import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.CallbackQueryHandler;
import com.valoler.telegram_hltv_bot.botapi.keyboards.handlers.callbackquery.CallbackQueryType;
import com.valoler.telegram_hltv_bot.service.ReplyMessageService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used only for test CallbackQueryParser.class
 *
 * @author VaolEr
 */

@RequiredArgsConstructor
public class TestCallbackQueryHandler implements CallbackQueryHandler {


    private static final CallbackQueryType HANDLER_QUERY_TYPE = BotCallbackQueryType.IMPLEMENTED$TEST;
    private final ReplyMessageService messageService;

    public static final String TEST_REPLY_MESSAGE = "TestMessage";


    @Override
    public SendMessage handleCallbackQuery(CallbackQuery callbackQuery) {

        final String chatID = callbackQuery.getMessage().getChatId().toString();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.setText(TEST_REPLY_MESSAGE);

        return sendMessage;
    }

    @Override
    public List<SendMessage> handleCallbackQueryMultiAnswer(CallbackQuery callbackQuery) {

        List<SendMessage> sendMessages = new ArrayList<>();

        Message message = callbackQuery.getMessage();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(TEST_REPLY_MESSAGE);

        sendMessages.add(sendMessage);

        return sendMessages;
    }

    @Override
    public CallbackQueryType getHandlerQueryType() {
        return HANDLER_QUERY_TYPE;
    }
}
