package com.example.auth_app.service;

import com.example.auth_app.entity.User;
import com.example.auth_app.repository.UserRepository;
import com.example.auth_app.util.JwtUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    public Map<String, Object> registerUser(String username, String email, String password) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Check if user exists
            if (userRepository.existsByUsername(username)) {
                response.put("success", false);
                response.put("message", "Username already exists!");
                return response;
            }
            
            if (userRepository.existsByEmail(email)) {
                response.put("success", false);
                response.put("message", "Email already exists!");
                return response;
            }
            
            // Hash password and save user
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            User user = new User(username, email, hashedPassword);
            userRepository.save(user);
            
            response.put("success", true);
            response.put("message", "User registered successfully!");
            return response;
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Registration failed: " + e.getMessage());
            return response;
        }
    }
    
    public Map<String, Object> authenticateUser(String usernameOrEmail, String password) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Optional<User> userOpt = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
            
            if (userOpt.isEmpty()) {
                response.put("success", false);
                response.put("message", "User not found!");
                return response;
            }
            
            User user = userOpt.get();
            
            if (!BCrypt.checkpw(password, user.getPassword())) {
                response.put("success", false);
                response.put("message", "Invalid password!");
                return response;
            }
            
            // Generate JWT token
            String token = jwtUtil.generateToken(user.getUsername());
            
            response.put("success", true);
            response.put("message", "Login successful!");
            response.put("token", token);
            response.put("user", user);
            return response;
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Login failed: " + e.getMessage());
            return response;
        }
    }
    
    public Map<String, Object> getDashboardData(String token) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            if (!jwtUtil.validateToken(token)) {
                response.put("success", false);
                response.put("message", "Invalid or expired token!");
                return response;
            }
            
            String username = jwtUtil.getUsernameFromToken(token);
            Optional<User> userOpt = userRepository.findByUsername(username);
            
            if (userOpt.isEmpty()) {
                response.put("success", false);
                response.put("message", "User not found!");
                return response;
            }
            
            User user = userOpt.get();
            response.put("success", true);
            response.put("message", "Welcome to your dashboard, " + user.getUsername() + "!");
            response.put("user", user);
            return response;
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Dashboard access failed: " + e.getMessage());
            return response;
        }
    }
}
