package com.klef.jfsd.sdp.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "teacher_table")
public class Teacher extends User {

    @Column(unique = true, nullable = false)  // Set as nullable if empId will be entered later
    private String empId;

    private Integer yearsOfExperience;  // Using Integer allows null initially

    @OneToMany(mappedBy = "teacher")
    private List<Achievement> achievementsReviewed;  // Nullable to allow optional assignment

    @Column(nullable = false)
    private boolean isFirstLogin = true;  // Flag to track first login

    // Getters and Setters
    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public List<Achievement> getAchievementsReviewed() {
        return achievementsReviewed;
    }

    public void setAchievementsReviewed(List<Achievement> achievementsReviewed) {
        this.achievementsReviewed = achievementsReviewed;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(boolean isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    @Override
    public String toString() {
        return "Teacher [fname=" + getFname() + ", lname=" + getLname() + 
               ", username=" + getUsername() + ", email=" + getEmail() + 
               ", yearsOfExperience=" + yearsOfExperience + 
               ", isFirstLogin=" + isFirstLogin + "]";
    }
}
