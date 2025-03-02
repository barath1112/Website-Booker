package com.realestate.service;

import com.realestate.dto.SignupRequest;
import com.realestate.model.User;
import com.realestate.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
    	
        this.userRepository = userRepository;
    
    
    }

        public boolean existsByEmail(String email) {
            return userRepository.existsByEmail(email);
        }
    

    public User registerUser(SignupRequest request) {
        // Check if email already exists
     
        // Create and save user
        User user = new User();
        user.setName(request.getName());
        user.setMobile(request.getMobile());
        user.setEmail(request.getEmail());
        user.setLocation(request.getLocation());
        user.setRole(request.getRole());
        user.setPassword(request.getPassword());  // Stored as plain text

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
