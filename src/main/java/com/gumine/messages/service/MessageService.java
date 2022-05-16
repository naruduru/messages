package com.gumine.messages.service;

import com.gumine.messages.repository.MessageRepository;
import com.gumine.messages.vo.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageService {
    private MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public Message save(String text) {
        return repository.saveMessage(new Message(text));
    }
}
