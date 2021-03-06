package ru.tlgrmbot.myhelperbot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/*
Формирует готовые ответные сообщения в чат.
 */
@Service
public class ReplyMessagesService {
    private LocaleMessageService localeMessageService;
    public ReplyMessagesService(LocaleMessageService messageService) {
        this.localeMessageService = messageService;
    }

    public SendMessage getReplyMessage(long chatId, String replyMessage) {
        return new SendMessage(chatId, localeMessageService.getMessage(replyMessage));
    }
}
