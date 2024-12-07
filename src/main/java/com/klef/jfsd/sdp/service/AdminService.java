package com.klef.jfsd.sdp.service;

import java.util.List;

import com.klef.jfsd.sdp.DTO.AdminSignUpRequest;
import com.klef.jfsd.sdp.DTO.AdminSignUpResponse;
import com.klef.jfsd.sdp.DTO.CreateEventRequest;
import com.klef.jfsd.sdp.DTO.CreateEventResponse;
import com.klef.jfsd.sdp.models.Student;

public interface AdminService {
    AdminSignUpResponse registerAdmin(AdminSignUpRequest adminSignUpRequest);
    List<Student> getAllStudents();
    
    
}
