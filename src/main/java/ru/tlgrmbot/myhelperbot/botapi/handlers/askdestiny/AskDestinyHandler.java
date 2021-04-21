package ru.tlgrmbot.myhelperbot.botapi.handlers.askdestiny;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.tlgrmbot.myhelperbot.botapi.BotState;
import ru.tlgrmbot.myhelperbot.botapi.InputMessageHandler;
import ru.tlgrmbot.myhelperbot.service.ReplyMessagesService;

/**
 * Спрашивает пользователя - хочет ли он получить предсказание.
 */

@Slf4j
@Component
public class AskDestinyHandler implements InputMessageHandler {
    private ReplyMessagesService messagesService;

    public AskDestinyHandler(ReplyMessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_HELPER;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    private SendMessage processUsersInput(Message inputMsg) {
        long chatId = inputMsg.getChatId();

        SendMessage replyToUser = messagesService.getReplyMessage(chatId, "reply.askHelper");

        return replyToUser;
    }
}
