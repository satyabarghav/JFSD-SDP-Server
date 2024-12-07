package com.klef.jfsd.sdp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.jfsd.sdp.models.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
	Teacher findByUsernameOrEmail(String username, String email);
	
	Teacher findByUsername(String username);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);
}
