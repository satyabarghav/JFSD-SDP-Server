package com.klef.jfsd.sdp.DTO;

public class AdminSignUpResponse {
    private String message;
    private String fname; // First name
    private String lname; // Last name
    private String email;
    private String username; // Include username in the response

    public AdminSignUpResponse(String message, String fname, String lname, String email, String username) {
        this.message = message;
        this.fname = fname; // Set first name in the constructor
        this.lname = lname; // Set last name in the constructor
        this.email = email;
        this.username = username; // Set username in the constructor
    }

    // Getters

    public String getMessage() {
        return message;
    }

    public String getFname() {
        return fname; // Getter for first name
    }

    public String getLname() {
        return lname; // Getter for last name
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username; // Getter for username
    }
}
