package com.realestate.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.realestate.dto.SignupRequest;
import com.realestate.model.User;
import com.realestate.repository.UserRepository;
import com.realestate.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")  // Allow frontend requests
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    // ✅ Constructor Injection (Fixed)
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        try {
            userService.registerUser(request);
            return ResponseEntity.ok("Signup successful!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/check-email")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        boolean exists = userService.existsByEmail(email);
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered");
        }
        return ResponseEntity.ok().body("Email available");
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(newPassword); // ⚠️ Consider encrypting the password
            userRepository.save(user);
            return ResponseEntity.ok("Password reset successfully!");
        } else {
            return ResponseEntity.badRequest().body("Email not found!");
        }
    }
}
