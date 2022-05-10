package com.gumine.messages.vo;

import lombok.Data;

@Data
public class Message {
    private String text;

    public Message(String text) {
        this.text = text;
    }
}
