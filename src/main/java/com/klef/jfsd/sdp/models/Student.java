package com.klef.jfsd.sdp.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_table")
public class Student extends User {

	 @Column(unique = true, nullable = false)
	    private String regNum;

	    @Column(length = 50)
	    private String department;

	    private Integer yearOfStudy;

	    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JsonManagedReference // Prevents infinite recursion
	    private List<Achievement> achievements;

	    private String dob;
	    private String contact;
	    private String address;

	    @Column(nullable = false)
	    private boolean isFirstLogin = true;

	// Getters and Setters

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(Integer yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	 @JsonManagedReference
	public List<Achievement> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<Achievement> achievements) {
		this.achievements = achievements;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isFirstLogin() {
		return isFirstLogin;
	}

	public void setFirstLogin(boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	@Override
	public String toString() {
		return "Student [fname=" + getFname() + ", lname=" + getLname() + ", department=" + department
				+ ", yearOfStudy=" + yearOfStudy + ", dob=" + dob + ", contact=" + contact + ", address=" + address
				+ ", isFirstLogin=" + isFirstLogin + "]";
	}

	public String getRegNum() {
		return regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
}
