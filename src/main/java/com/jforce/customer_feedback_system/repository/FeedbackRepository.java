package com.jforce.customer_feedback_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jforce.customer_feedback_system.entity.Feedback;
import com.jforce.customer_feedback_system.entity.User;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    // Get all feedback submitted by a user
    List<Feedback> findByUser(User user);
    
    Feedback findFirstByUser(User user);

}

