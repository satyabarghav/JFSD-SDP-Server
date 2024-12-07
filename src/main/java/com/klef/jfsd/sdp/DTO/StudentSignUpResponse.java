package com.klef.jfsd.sdp.DTO;

public class StudentSignUpResponse {
    private String message;
    private String fname;  // First name
    private String lname;  // Last name
    private String email;
    private String username; // Username field
    private String regNum;

    public StudentSignUpResponse(String message, String fname, String lname, String email, String username,String regNum) {
        this.message = message;
        this.fname = fname; // Assign first name in constructor
        this.lname = lname; // Assign last name in constructor
        this.email = email;
        this.username = username; // Assign username in constructor
        this.regNum = regNum;
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
        return username;
    }

	public String getRegNum() {
		return regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
}
