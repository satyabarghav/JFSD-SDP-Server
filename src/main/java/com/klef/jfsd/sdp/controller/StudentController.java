package com.klef.jfsd.sdp.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.sdp.DTO.ResetPasswordRequest;
import com.klef.jfsd.sdp.DTO.StudentSignUpRequest;
import com.klef.jfsd.sdp.DTO.StudentSignUpResponse;
import com.klef.jfsd.sdp.models.Student;
import com.klef.jfsd.sdp.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<StudentSignUpResponse> registerStudent(@RequestBody StudentSignUpRequest studentSignUpRequest) {
        StudentSignUpResponse response = studentService.registerStudent(studentSignUpRequest);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/batch-register", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, List<String>>> registerStudentsBatch(@RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", List.of("File is empty")));
        }
        try {
            Map<String, List<String>> result = studentService.registerStudentsBatch(file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", List.of("Failed to process batch registration: " + e.getMessage())));
        }
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/viewallstudents")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") UUID id) {
        boolean isDeleted = studentService.deleteStudent(id);
        if (isDeleted) {
            return ResponseEntity.ok("Student deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }
    @PutMapping("/reset-password")
    @PreAuthorize("hasRole('STUDENT') or hasRole('ADMIN')")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
    	System.out.println(resetPasswordRequest.getEmail());
        String response = studentService.resetPassword(resetPasswordRequest);
        return ResponseEntity.ok(response);
    }

}
