package com.klef.jfsd.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.klef.jfsd.sdp.DTO.AdminSignUpRequest;
import com.klef.jfsd.sdp.DTO.AdminSignUpResponse;
import com.klef.jfsd.sdp.DTO.CreateEventRequest;
import com.klef.jfsd.sdp.DTO.CreateEventResponse;
import com.klef.jfsd.sdp.models.Admin;
import com.klef.jfsd.sdp.models.Event;
import com.klef.jfsd.sdp.models.Role;
import com.klef.jfsd.sdp.models.Student;
import com.klef.jfsd.sdp.repository.AdminRepository;
import com.klef.jfsd.sdp.repository.EventRepository;
import com.klef.jfsd.sdp.repository.StudentRepository;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    

    @Override
    public AdminSignUpResponse registerAdmin(AdminSignUpRequest adminSignUpRequest) {
        Admin admin = new Admin();
        admin.setFname(adminSignUpRequest.getFname());
        admin.setLname(adminSignUpRequest.getLname());
        admin.setEmail(adminSignUpRequest.getEmail());
        admin.setUsername(adminSignUpRequest.getUsername());

        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(adminSignUpRequest.getPassword());
        admin.setPassword(hashedPassword);
        admin.setRole(Role.ADMIN);

        // Save admin entity to database
        adminRepository.save(admin);

        // Create and return response
        return new AdminSignUpResponse("Admin registration successful!", admin.getFname(),admin.getLname(), admin.getEmail(), admin.getUsername());
    }

	@Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
	}
		
}
