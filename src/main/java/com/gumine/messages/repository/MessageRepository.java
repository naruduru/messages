package com.gumine.messages.repository;

import com.gumine.messages.vo.Message;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageRepository {
    private final static Log log = LogFactory.getLog(MessageRepository.class);

    public void saveMessage(Message message) {
        // 데이터베이스에 메시지를 저장한다.
        log.info("Saved message: " + message.getText());
    }
}