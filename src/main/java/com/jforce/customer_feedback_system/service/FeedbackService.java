package com.jforce.customer_feedback_system.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jforce.customer_feedback_system.entity.Feedback;
import com.jforce.customer_feedback_system.entity.User;
import com.jforce.customer_feedback_system.repository.FeedbackRepository;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Add Feedback
    public String addFeedback(User user, String feedbackText) {

    	Feedback feedback = feedbackRepository.findFirstByUser(user);

    	if (feedback == null) {
    	    feedback = new Feedback();
    	    feedback.setUser(user);
    	}

    	feedback.setFeedback(feedbackText);
    	feedback.setDate(LocalDate.now());

    	feedbackRepository.save(feedback);
		return feedbackText;
    }

    // Update Feedback
    public String updateFeedback(int id, String feedbackText) {

        Feedback feedback = feedbackRepository.findById(id).orElse(null);

        if (feedback == null) {
            return "Feedback Not Found";
        }

        feedback.setFeedback(feedbackText);

        feedbackRepository.save(feedback);

        return "Feedback Updated Successfully";
    }

    // Delete Feedback
    public String deleteFeedback(int id) {

        feedbackRepository.deleteById(id);

        return "Feedback Deleted Successfully";
    }

    // View All Feedback
    public List<Feedback> getAllFeedback() {

        return feedbackRepository.findAll();
    }

    // View Feedback By User
    public List<Feedback> getFeedbackByUser(User user) {

        return feedbackRepository.findByUser(user);
    }
    
 // Get Single Feedback Of User
    public Feedback getSingleFeedbackByUser(User user) {

        return feedbackRepository.findFirstByUser(user);

    }
    
    // admin service method 
    public Feedback getFeedbackById(int id) {

        return feedbackRepository.findById(id).orElse(null);

    }

}