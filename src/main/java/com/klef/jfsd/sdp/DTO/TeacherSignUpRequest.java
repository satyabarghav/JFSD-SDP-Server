package com.klef.jfsd.sdp.DTO;

public class TeacherSignUpRequest {
    private String fname;             // First name
    private String lname;             // Last name
    private String email;             // Email
    private String username;          // Username
    private String password;          // Password
    private int yearsOfExperience;    // Years of experience
    private String empId;

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
        return username; 
    }

    public void setUsername(String username) {
        this.username = username; 
    }

    public String getPassword() {
        return password; 
    }

    public void setPassword(String password) {
        this.password = password; 
    }

    public int getYearsOfExperience() {
        return yearsOfExperience; 
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience; 
    }

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
}
