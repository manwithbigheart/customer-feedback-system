package com.jforce.customer_feedback_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import com.jforce.customer_feedback_system.entity.User;
import com.jforce.customer_feedback_system.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Open Login Page
    @GetMapping("/")
    public String home() {
        return "login";
    }

    // Open Register Page
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // Register User
    @PostMapping("/user/register")
    public String register(String username,
                           String email,
                           String password) {

        User user = new User();

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        userService.register(user);

        return "login";
    }

    // Login User
    @PostMapping("/user/login")
    public String login(String username,
                        String password,
                        HttpSession session) {

        User user = userService.login(username, password);

        if(user != null) {

            // Store logged in user in session
            session.setAttribute("loggedInUser", user);

            return "redirect:/feedback";
        }

        return "login";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/";

    }

}