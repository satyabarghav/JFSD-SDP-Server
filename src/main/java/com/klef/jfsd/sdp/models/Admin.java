package com.klef.jfsd.sdp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_table")
public class Admin extends User {
    // No additional fields for Admin in this example
    
    @Override
    public String toString() {
        return "Admin [fname=" + getFname() + ", lname=" + getLname() + ", username=" + getUsername() + ", email=" + getEmail() + ", role=" + getRole() + "]";
    }
}
