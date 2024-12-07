package com.klef.jfsd.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.jfsd.sdp.DTO.AdminSignUpRequest;
import com.klef.jfsd.sdp.DTO.AdminSignUpResponse;
import com.klef.jfsd.sdp.models.Student;
import com.klef.jfsd.sdp.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

	@PostMapping("/createadmin")
    public ResponseEntity<AdminSignUpResponse> registerAdmin(@RequestBody AdminSignUpRequest adminSignUpRequest) {
        AdminSignUpResponse response = adminService.registerAdmin(adminSignUpRequest);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/viewallstudents")
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = adminService.getAllStudents();
        return ResponseEntity.ok(students);
    }
}
