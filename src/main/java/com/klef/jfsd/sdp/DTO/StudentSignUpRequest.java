package com.klef.jfsd.sdp.DTO;

public class StudentSignUpRequest {
	private String fname; // First name
	private String lname; // Last name
	private String email;
	private String username; // Username field
	private String regNum;

	// Getters and Setters

	public String getFname() {
		return fname; // Getter for first name
	}

	public void setFname(String fname) {
		this.fname = fname; // Setter for first name
	}

	public String getLname() {
		return lname; // Getter for last name
	}

	public void setLname(String lname) {
		this.lname = lname; // Setter for last name
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRegNum() {
		return regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
}
