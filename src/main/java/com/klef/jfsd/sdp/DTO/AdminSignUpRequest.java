package com.klef.jfsd.sdp.DTO;

public class AdminSignUpRequest {
    private String fname;  // First name
    private String lname;  // Last name
    private String email;
    private String username; // Username field
    private String password;

    // Getters and Setters

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username; // Getter for username
    }

    public void setUsername(String username) {
        this.username = username; // Setter for username
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
