package com.example.auth_app.controller;

import com.example.auth_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class WebController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }
    
    @GetMapping("/signup")
    public String showSignup(Model model,
                            @RequestParam(required = false) String error,
                            @RequestParam(required = false) String success) {
        if (error != null) model.addAttribute("error", error);
        if (success != null) model.addAttribute("success", success);
        return "signup";
    }
    
    @PostMapping("/signup")
    public String handleSignup(@RequestParam String username,
                              @RequestParam String email,
                              @RequestParam String password) {
        Map<String, Object> result = userService.registerUser(username, email, password);
        
        if ((Boolean) result.get("success")) {
            return "redirect:/login?success=Registration successful! Please login.";
        } else {
            return "redirect:/signup?error=" + result.get("message");
        }
    }
    
    @GetMapping("/login")
    public String showLogin(Model model,
                           @RequestParam(required = false) String error,
                           @RequestParam(required = false) String success) {
        if (error != null) model.addAttribute("error", error);
        if (success != null) model.addAttribute("success", success);
        return "login";
    }
    
    @PostMapping("/login")
    public String handleLogin(@RequestParam String usernameOrEmail,
                             @RequestParam String password) {
        Map<String, Object> result = userService.authenticateUser(usernameOrEmail, password);
        
        if ((Boolean) result.get("success")) {
            String token = (String) result.get("token");
            return "redirect:/dashboard?token=" + token;
        } else {
            return "redirect:/login?error=" + result.get("message");
        }
    }
    
    @GetMapping("/dashboard")
    public String showDashboard(Model model, @RequestParam(required = false) String token) {
        if (token == null || token.isEmpty()) {
            return "redirect:/login?error=Please login first";
        }
        
        Map<String, Object> result = userService.getDashboardData(token);
        
        if ((Boolean) result.get("success")) {
            model.addAttribute("user", result.get("user"));
            model.addAttribute("message", result.get("message"));
            model.addAttribute("token", token);
            return "dashboard";
        } else {
            return "redirect:/login?error=" + result.get("message");
        }
    }
}
