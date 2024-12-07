package com.klef.jfsd.sdp.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.sdp.DTO.ResetPasswordRequest;
import com.klef.jfsd.sdp.DTO.StudentSignUpRequest;
import com.klef.jfsd.sdp.DTO.StudentSignUpResponse;
import com.klef.jfsd.sdp.models.Student;

public interface StudentService {
    StudentSignUpResponse registerStudent(StudentSignUpRequest studentSignUpRequest);
    String resetPassword(ResetPasswordRequest resetPasswordRequest);
    List<Student> getAllStudents();
    boolean deleteStudent(UUID id);
	Map<String, List<String>> registerStudentsBatch(MultipartFile file) throws IOException;
}
