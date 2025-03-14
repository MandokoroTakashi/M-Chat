package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Message;
import com.example.demo.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // 全メッセージを取得
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    // 特定のユーザーのメッセージを取得
    public List<Message> getMessagesByUserId(Long userId) {
        return messageRepository.findByUserId(userId);
    }

    // 新規メッセージを作成
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }
}
