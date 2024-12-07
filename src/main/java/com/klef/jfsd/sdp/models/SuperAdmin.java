package com.klef.jfsd.sdp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "superadmin_table")
public class SuperAdmin extends User {
	@Override
	public String toString() {
		return "SuperAdmin [id=" + getId() + ", fname=" + getFname() + ", lname=" + getLname() + ", username="
				+ getUsername() + ", email=" + getEmail() + ", role=" + getRole() + "]";
	}

}
