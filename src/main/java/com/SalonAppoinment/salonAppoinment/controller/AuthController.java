package com.SalonAppoinment.salonAppoinment.controller;

import com.SalonAppoinment.salonAppoinment.dto.UserRequestDTO;
import com.SalonAppoinment.salonAppoinment.dto.UserResponseDTO;
import com.SalonAppoinment.salonAppoinment.entity.User;
import com.SalonAppoinment.salonAppoinment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequestDTO request) {
        try {
            User user = new User(request.getName(), request.getUsername(), request.getPassword(), request.getRole(), request.getEmail(), request.getContactNumber());
            User saved = userService.registerUser(user);
            return ResponseEntity.ok(new UserResponseDTO(saved.getId(), saved.getUsername(), saved.getUsername(), saved.getRole()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserRequestDTO request) {
        Optional<User> userOpt = userService.loginUser(request.getUsername(), request.getPassword());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return ResponseEntity.ok(new UserResponseDTO(user.getId(), user.getUsername(),user.getRole(), user.getName()));
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}

