package com.jforce.customer_feedback_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

import com.jforce.customer_feedback_system.entity.Feedback;
import com.jforce.customer_feedback_system.service.FeedbackService;

@Controller
public class AdminController {

    @Autowired
    private FeedbackService feedbackService;

    // Open Admin Login
    @GetMapping("/admin")
    public String adminLoginPage() {
        return "admin-login";
    }

    // Admin Login
    @PostMapping("/admin/login")
    public String adminLogin(String username,
                             String password,
                             HttpSession session) {

        if(username.equals("admin") && password.equals("admin123")) {

            session.setAttribute("admin", "admin");

            return "redirect:/admin/dashboard";
        }

        return "admin-login";
    }

    // Dashboard
    @GetMapping("/admin/dashboard")
    public String dashboard(HttpSession session,
                            Model model) {

        if(session.getAttribute("admin")==null) {

            return "redirect:/admin";
        }

        List<Feedback> list = feedbackService.getAllFeedback();

        model.addAttribute("feedbackList", list);

        return "admin-dashboard";
    }

    // Delete Feedback
    @GetMapping("/admin/delete")
    public String deleteFeedback(int id,
                                 HttpSession session) {

        if(session.getAttribute("admin")==null) {

            return "redirect:/admin";
        }

        feedbackService.deleteFeedback(id);

        return "redirect:/admin/dashboard";
    }

    // Logout
    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/admin";
    }
    
    @GetMapping("/admin/edit")
    public String editFeedback(int id,
                               Model model,
                               HttpSession session) {

        if(session.getAttribute("admin")==null){
            return "redirect:/admin";
        }

        Feedback feedback = feedbackService.getFeedbackById(id);

        model.addAttribute("feedback", feedback);

        return "admin-edit";

    }
    @PostMapping("/admin/update")
    public String updateFeedback(int id,
                                 String feedback,
                                 HttpSession session) {

        if(session.getAttribute("admin")==null){

            return "redirect:/admin";
        }

        feedbackService.updateFeedback(id, feedback);

        return "redirect:/admin/dashboard";

    }

}