package com.klef.jfsd.sdp.DTO;

public class AdminLoginResponse {
    private String fname;  // First name
    private String lname;  // Last name
    private String username;
    private String email;

    // Constructor
    public AdminLoginResponse(String fname, String lname, String username, String email) {
        this.fname = fname; // Set first name
        this.lname = lname; // Set last name
        this.username = username;
        this.email = email;
    }

    // Getters
    public String getFname() {
        return fname; // Getter for first name
    }

    public String getLname() {
        return lname; // Getter for last name
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
