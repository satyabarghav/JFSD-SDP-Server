package com.klef.jfsd.sdp.DTO;

import java.util.UUID;

public class UserDetailsResponse {
    private String firstName;   // First name
    private String lastName;    // Last name
    private String email;       // Email
    private String username;    // Username
    private String role;        // User role (Admin/Student/Teacher)
    private String token;
    private boolean isFirstLogin;
    private UUID id;
    private String regNum;

    public UserDetailsResponse(String firstName, String lastName, String username, String email, String role,UUID id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.role = role;
        this.id = id;
    }
    
    public UserDetailsResponse(String firstName, String lastName, String username, String email, String role,boolean isFirstLogin,UUID id,String regNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.role = role;
        this.isFirstLogin = isFirstLogin;
        this.id = id;
        this.regNum = regNum;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
    
    public String getToken() {
    	return token;
    }
    
    public void setToken(String token) {
    	this.token = token;
    }

	public boolean isFirstLogin() {
		return isFirstLogin;
	}

	public void setFirstLogin(boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}

	public String getRegNum() {
		return regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
}
