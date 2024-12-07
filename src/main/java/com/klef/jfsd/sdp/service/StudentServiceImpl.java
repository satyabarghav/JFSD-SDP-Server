package com.klef.jfsd.sdp.service;

import java.io.IOException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.sdp.DTO.ResetPasswordRequest;
import com.klef.jfsd.sdp.DTO.StudentSignUpRequest;
import com.klef.jfsd.sdp.DTO.StudentSignUpResponse;
import com.klef.jfsd.sdp.models.Role;
import com.klef.jfsd.sdp.models.Student;
import com.klef.jfsd.sdp.repository.StudentRepository;

import jakarta.mail.MessagingException;

@Service
public class StudentServiceImpl implements StudentService {

    private static final String[] STUDENT_HEADERS = {
        "FirstName", "LastName", "Email", "Username", "RegNum"
    };

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CSVService csvService;

    @Override
    public StudentSignUpResponse registerStudent(StudentSignUpRequest studentSignUpRequest) {
        // Check for duplicate email or username
        if (studentRepository.existsByEmail(studentSignUpRequest.getEmail())) {
            throw new IllegalArgumentException("A student with this email already exists.");
        }
        if (studentRepository.existsByUsername(studentSignUpRequest.getUsername())) {
            throw new IllegalArgumentException("A student with this username already exists.");
        }

        Student student = new Student();
        student.setFname(studentSignUpRequest.getFname());
        student.setLname(studentSignUpRequest.getLname());
        student.setEmail(studentSignUpRequest.getEmail());
        student.setUsername(studentSignUpRequest.getUsername());
        student.setRegNum(studentSignUpRequest.getRegNum());

        // Generate a random temporary password
        String temporaryPassword = UUID.randomUUID().toString().substring(0, 8);
        System.out.println("Random Password = "+temporaryPassword);
        String hashedPassword = passwordEncoder.encode(temporaryPassword);
        student.setPassword(hashedPassword);
        student.setRole(Role.STUDENT);

        // Save student entity to database
        studentRepository.save(student);

        // Send the email with the temporary password
        try {
            emailService.sendTemporaryPasswordEmail(
                student.getFname() + " " + student.getLname(),
                student.getUsername(),
                student.getEmail(),
                temporaryPassword
            );
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email to: " + student.getEmail());
        }

        // Create and return response
        return new StudentSignUpResponse(
            "Student registration successful! A temporary password has been sent to your email.",
            student.getFname(),
            student.getLname(),
            student.getEmail(),
            student.getUsername(),
            student.getRegNum()
        );
    }

    @Override
    public String resetPassword(ResetPasswordRequest resetPasswordRequest) {
        Student student = studentRepository.findByEmail(resetPasswordRequest.getEmail());
        if (student == null) {
            return "User with that email doesn't exist";
        }

        if (resetPasswordRequest.getCurrentPassword().equals(resetPasswordRequest.getNewPassword())) {
            return "Current Password is same as New Password";
        }

        if (!passwordEncoder.matches(resetPasswordRequest.getCurrentPassword(), student.getPassword())) {
            return "Current Password is wrong!";
        }

        String newHashedPassword = passwordEncoder.encode(resetPasswordRequest.getNewPassword());
        student.setPassword(newHashedPassword);
        student.setFirstLogin(false);

        studentRepository.save(student);

        return "Password Reset Successful!";
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public boolean deleteStudent(UUID id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
            return true;
        }
        return false;
    }

    @Override
    public Map<String, List<String>> registerStudentsBatch(MultipartFile file) throws IOException {
        List<String> successes = new ArrayList<>();
        List<String> failures = new ArrayList<>();

        List<StudentSignUpRequest> studentRequests = csvService.parseCsv(file, STUDENT_HEADERS, record -> {
            StudentSignUpRequest request = new StudentSignUpRequest();
            request.setFname(record.get("FirstName"));
            request.setLname(record.get("LastName"));
            request.setEmail(record.get("Email"));
            request.setUsername(record.get("Username"));
            request.setRegNum(record.get("RegNum"));
            return request;
        });

        for (StudentSignUpRequest request : studentRequests) {
            try {
                registerStudent(request);
                successes.add("Successfully registered: " + request.getEmail());
            } catch (Exception e) {
                failures.add("Failed to register: " + request.getEmail() + " - " + e.getMessage());
            }
        }

        Map<String, List<String>> result = new HashMap<>();
        result.put("successes", successes);
        result.put("failures", failures);

        return result;
    }
}
