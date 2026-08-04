package com.jforce.customer_feedback_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jforce.customer_feedback_system.entity.User;
import com.jforce.customer_feedback_system.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register User
    public String register(User user) {

        userRepository.save(user);

        return "User Registered Successfully";
    }

 // Login User
    public User login(String username, String password) {

        return userRepository.findByUsernameAndPassword(username, password);

    }

    // Get User By ID
    public User getUserById(int id) {

        return userRepository.findById(id).orElse(null);
    }

}