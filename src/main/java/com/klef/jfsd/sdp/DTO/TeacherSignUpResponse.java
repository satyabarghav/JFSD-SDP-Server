package com.klef.jfsd.sdp.DTO;

public class TeacherSignUpResponse {
    private String message;      // Response message
    private String fname;        // First name
    private String lname;        // Last name
    private String email;        // Email
    private String username;     // Username
    private String empId;
 

    public TeacherSignUpResponse(String message, String fname, String lname, String email, String username,String empId) {
        this.message = message;
        this.fname = fname;      // Assign first name in constructor
        this.lname = lname;      // Assign last name in constructor
        this.email = email;
        this.username = username; // Assign username in constructor
        this.empId = empId;
    }

    public TeacherSignUpResponse(String message) {
		this.message = message;
	}

	// Getters
    public String getMessage() {
        return message;
    }

    public String getFname() {
        return fname;  // Getter for first name
    }

    public String getLname() {
        return lname;  // Getter for last name
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
}
