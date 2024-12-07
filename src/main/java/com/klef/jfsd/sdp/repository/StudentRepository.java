package com.klef.jfsd.sdp.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.jfsd.sdp.models.Student;
import java.util.List;


public interface StudentRepository extends JpaRepository<Student,UUID> {

	Student findByEmail(String email);

	Student findByUsername(String username);

	Optional<Student> findByRegNum(String regNum);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

	
}
