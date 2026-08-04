package com.jforce.customer_feedback_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

import com.jforce.customer_feedback_system.entity.Feedback;
import com.jforce.customer_feedback_system.entity.User;
import com.jforce.customer_feedback_system.service.FeedbackService;
import com.jforce.customer_feedback_system.service.UserService;

@Controller
public class FeedbackController {
	

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    // Add Feedback
    @PostMapping("/feedback/add")
    public String addFeedback(int id,
                              String feedback,
                              HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/";
        }

        if (id == 0) {
            feedbackService.addFeedback(user, feedback);
        } else {
            feedbackService.updateFeedback(id, feedback);
        }

        return "redirect:/feedback";
    }

    // Update Feedback
    @GetMapping("/feedback/update")
    public String updateFeedback(int id,
                                 String feedback) {

        return feedbackService.updateFeedback(id, feedback);

    }

    // View Feedback Of User
    @GetMapping("/feedback")
    public String feedbackPage(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/";
        }

        Feedback feedback = feedbackService.getSingleFeedbackByUser(user);

        model.addAttribute("username", user.getUsername());
        model.addAttribute("feedback", feedback);
        model.addAttribute("feedbackList",
                feedbackService.getFeedbackByUser(user));

        return "feedback";
    }

}