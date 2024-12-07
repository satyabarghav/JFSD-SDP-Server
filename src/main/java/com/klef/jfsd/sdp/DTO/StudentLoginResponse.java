package com.klef.jfsd.sdp.DTO;

public class StudentLoginResponse {
    private String fname;      // First name
    private String lname;      // Last name
    private String email;      // Email
    private String username;    // Username
    private String regNum;

    // Constructor
    public StudentLoginResponse(String fname, String lname, String email, String username,String regNum) {
        this.fname = fname; 
        this.lname = lname; 
        this.email = email; 
        this.username = username; // Set username in constructor
        this.regNum = regNum;
        
    }

    // Getters
    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

	public String getRegNum() {
		return regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
}
