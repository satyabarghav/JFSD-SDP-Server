package com.klef.jfsd.sdp.service;

import org.springframework.security.core.userdetails.UserDetails;
import com.klef.jfsd.sdp.DTO.UserDetailsResponse; // Create a new DTO for user details
import com.klef.jfsd.sdp.models.User;

public interface UserService {
    User authenticateUser(String usernameOrEmail, String password);
    UserDetailsResponse getUserDetails(User user); // New method to get user details
    UserDetails getByUsername(String username) throws Exception;
}
