package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 全ユーザーを取得
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // IDでユーザーを取得
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // ユーザー名で取得
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 新規ユーザーを作成
    public User createUser(User user) {
    	User userData = new User();
    	
    	userData.setUsername(user.getUsername());
    	userData.setEmail(user.getEmail());
    	userData.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(userData);
    }
}
