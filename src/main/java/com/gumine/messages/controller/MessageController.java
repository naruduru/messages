package com.gumine.messages.controller;

import com.gumine.messages.service.MessageService;
import com.gumine.messages.vo.Message;
import com.gumine.messages.vo.MessageData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/messages")
public class MessageController {
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("message", "Hello, Welcome to Spring Boot!");
        return "welcome";
    }

    @PostMapping("/saveMessage")
    @ResponseBody
    public ResponseEntity<Message> saveMessage(@RequestBody MessageData data) {
         Message saved = messageService.save(data.getText());

        if (saved == null) {
            return ResponseEntity.status(500).build();
        }

        return ResponseEntity.ok(saved);
    }
}
