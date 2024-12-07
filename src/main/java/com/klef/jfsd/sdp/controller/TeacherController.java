package com.klef.jfsd.sdp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.sdp.DTO.TeacherSignUpRequest;
import com.klef.jfsd.sdp.DTO.TeacherSignUpResponse;
import com.klef.jfsd.sdp.models.Teacher;
import com.klef.jfsd.sdp.service.TeacherService;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // Single teacher registration
    @PostMapping("/register")
    public ResponseEntity<TeacherSignUpResponse> registerTeacher(@RequestBody TeacherSignUpRequest teacherSignUpRequest) {
        try {
            TeacherSignUpResponse response = teacherService.registerTeacher(teacherSignUpRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TeacherSignUpResponse(e.getMessage()));
        }
    }

    // View all teachers (Admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/viewallteachers")
    public ResponseEntity<List<Teacher>> getTeachers() {
        try {
            List<Teacher> teachers = teacherService.getAllTeachers();
            return ResponseEntity.ok(teachers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Delete teacher by ID (Admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable("id") UUID eid) {
        try {
            boolean isDeleted = teacherService.deleteTeacher(eid);
            if (isDeleted) {
                return ResponseEntity.ok("Teacher Deleted Successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

    // Batch register teachers using CSV (Admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/batch-register", consumes = "multipart/form-data")
    public ResponseEntity<String> registerTeachersBatch(@RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty. Please upload a valid CSV file.");
        }

        try {
            // Call the service to process the file
            Map<String, List<String>> result = teacherService.registerTeachersBatch(file);

            // Build response
            String responseMessage = "Batch Registration Completed\n"
                    + "Successfully Registered: " + result.get("successes").size() + "\n"
                    + "Failed Registrations: " + result.get("failures").size();
            return ResponseEntity.ok(responseMessage);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing file: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred: " + e.getMessage());
        }
    }
}
