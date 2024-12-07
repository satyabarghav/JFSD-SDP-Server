package com.klef.jfsd.sdp.DTO;

public class TeacherLoginResponse {
    private String fname;               // First name
    private String lname;               // Last name
    private String email;               // Email
    private String username;            // Username
    private int yearsOfExperience;      // Years of experience
    private String empId;

    public TeacherLoginResponse(String fname, String lname, String email, String username, int yearsOfExperience,String empId) {
        this.fname = fname;              // Assign first name in constructor
        this.lname = lname;              // Assign last name in constructor
        this.email = email;
        this.username = username;
        this.yearsOfExperience = yearsOfExperience;
        this.empId = empId;
    }

    // Getters
    public String getFname() {
        return fname;                     // Getter for first name
    }

    public String getLname() {
        return lname;                     // Getter for last name
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
}
