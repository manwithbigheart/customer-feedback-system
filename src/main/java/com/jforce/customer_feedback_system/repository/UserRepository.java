package com.jforce.customer_feedback_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jforce.customer_feedback_system.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    // Used during login
    User findByUsernameAndPassword(String username, String password);

}