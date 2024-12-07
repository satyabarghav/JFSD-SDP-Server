package com.klef.jfsd.sdp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.jfsd.sdp.models.SuperAdmin;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, UUID> {

	SuperAdmin findByUsername(String username);
	

}
