package com.klef.jfsd.sdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.jfsd.sdp.DTO.AdminSignUpRequest;
import com.klef.jfsd.sdp.DTO.AdminSignUpResponse;
import com.klef.jfsd.sdp.service.AdminService;


@RestController
@RequestMapping("/api/super-admin")
public class SuperAdminController {
	
	@Autowired
	AdminService adminService;
	
	@PreAuthorize("hasRole('SUPERADMIN')")
	@PostMapping("/createadmin")
    public ResponseEntity<AdminSignUpResponse> registerAdmin(@RequestBody AdminSignUpRequest adminSignUpRequest) {
        AdminSignUpResponse response = adminService.registerAdmin(adminSignUpRequest);
        return ResponseEntity.ok(response);
    }
}
