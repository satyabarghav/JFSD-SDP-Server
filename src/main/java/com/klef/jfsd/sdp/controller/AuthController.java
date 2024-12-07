package com.klef.jfsd.sdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.jfsd.sdp.DTO.LoginRequest;
import com.klef.jfsd.sdp.DTO.UserDetailsResponse;
import com.klef.jfsd.sdp.models.User;
import com.klef.jfsd.sdp.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Authenticate the user
        User user = userService.authenticateUser(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());

        if (user == null) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        // Retrieve user details based on role
        UserDetailsResponse userDetailsResponse = userService.getUserDetails(user);
        
        if (userDetailsResponse == null) {
            return ResponseEntity.status(404).body("User details not found");
        }

        return ResponseEntity.ok(userDetailsResponse);
    }
}