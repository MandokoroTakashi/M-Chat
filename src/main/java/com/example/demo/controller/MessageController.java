package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8081") // VueのURL（ポート8081）を許可
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    // 全メッセージ取得
    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    // 特定のユーザーのメッセージ取得
    // ユーザーIDでメッセージを取得
    @GetMapping("/user/{userId}")
    public List<Message> getMessagesByUserId(@PathVariable Long userId) {
        return messageService.getMessagesByUserId(userId);
    }

    @PostMapping
    public Message createMessage(@RequestBody Message message) {
        // user_idをもとにUserを取得
        User user = userService.getUserById(message.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        // messageにuser_idを設定（もし必要であれば）
        message.setUserId(user.getId());

        return messageService.createMessage(message);
    }
}
