package ru.tlgrmbot.myhelperbot.botapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class TelegramFacade {
    private BotStateContext botStateContext;

    public TelegramFacade(BotStateContext botStateContext) {
        this.botStateContext = botStateContext;
    }

    public SendMessage handleUpdate(Update update) {
        SendMessage replyMessage = null;

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            log.info("New message from User: {}, chatId: {}, with text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());
            replyMessage = handleInputMessage(message);
        }

        return replyMessage;
    }

    private SendMessage handleInputMessage(Message message) {
        String inputMsg = message.getText();
        BotState botState;
        SendMessage replyMessage;

        //проверка ВВОДА от пользователя - боту
        switch (inputMsg) {
            case "/start":
                botState = BotState.ASK_HELPER;
                break;
            default:
                botState = BotState.ASK_HELPER;
                break;
        }
        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }
}