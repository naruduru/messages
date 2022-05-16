package com.gumine.messages.vo;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private Integer id;
    private String text;
    private Date createDate;

    public Message(String text) {
        this.text = text;
        this.createDate = new Date();
    }

    public Message(int id, String text, Date createDate) {
        this.id = id;
        this.text = text;
        this.createDate = createDate;
    }
}
