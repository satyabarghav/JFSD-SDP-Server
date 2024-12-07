package com.klef.jfsd.sdp.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.sdp.DTO.TeacherSignUpRequest;
import com.klef.jfsd.sdp.DTO.TeacherSignUpResponse;
import com.klef.jfsd.sdp.models.Role;
import com.klef.jfsd.sdp.models.Teacher;
import com.klef.jfsd.sdp.repository.TeacherRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TeacherServiceImpl implements TeacherService {


    private static final String[] TEACHER_HEADERS = {
        "FirstName", "LastName", "Email", "Username", "EmployeeID", "Password", "YearsOfExperience"
    };

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CSVService csvService;

    @Override
    public TeacherSignUpResponse registerTeacher(TeacherSignUpRequest teacherSignUpRequest) {
        // Validate email and username
        if (teacherRepository.existsByEmail(teacherSignUpRequest.getEmail())) {
            throw new IllegalArgumentException("A teacher with this email already exists.");
        }
        if (teacherRepository.existsByUsername(teacherSignUpRequest.getUsername())) {
            throw new IllegalArgumentException("A teacher with this username already exists.");
        }

        Teacher teacher = new Teacher();
        teacher.setFname(teacherSignUpRequest.getFname());
        teacher.setLname(teacherSignUpRequest.getLname());
        teacher.setEmail(teacherSignUpRequest.getEmail());
        teacher.setUsername(teacherSignUpRequest.getUsername());
        teacher.setEmpId(teacherSignUpRequest.getEmpId());

        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(teacherSignUpRequest.getPassword());
        teacher.setPassword(hashedPassword);
        teacher.setRole(Role.TEACHER);
        teacher.setYearsOfExperience(teacherSignUpRequest.getYearsOfExperience());

        // Save teacher entity to database
        teacherRepository.save(teacher);

        // Create and return response
        return new TeacherSignUpResponse(
            "Teacher registration successful!",
            teacher.getFname(),
            teacher.getLname(),
            teacher.getEmail(),
            teacher.getUsername(),
            teacher.getEmpId()
        );
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public boolean deleteTeacher(UUID id) {
        if (!teacherRepository.existsById(id)) {
            throw new EntityNotFoundException("Teacher with ID " + id + " not found.");
        }
        teacherRepository.deleteById(id);
        return true;
    }

    @Override
    public Map<String, List<String>> registerTeachersBatch(MultipartFile file) throws IOException {
//		return null;
        List<String> successes = new ArrayList<>();
        List<String> failures = new ArrayList<>();

        List<TeacherSignUpRequest> teacherRequests = csvService.parseCsv(file, TEACHER_HEADERS, record -> {
            TeacherSignUpRequest request = new TeacherSignUpRequest();
            request.setFname(record.get("FirstName"));
            request.setLname(record.get("LastName"));
            request.setEmail(record.get("Email"));
            request.setUsername(record.get("Username"));
            request.setEmpId(record.get("EmployeeID"));
            request.setPassword(record.get("Password"));
            request.setYearsOfExperience(Integer.parseInt(record.get("YearsOfExperience")));
            return request;
        });

        for (TeacherSignUpRequest request : teacherRequests) {
            try {
                registerTeacher(request);
                successes.add("Successfully registered: " + request.getEmail());
            } catch (Exception e) {
                //logger.error("Failed to register teacher: {}. Error: {}", request.getEmail(), e.getMessage());
                failures.add("Failed to register: " + request.getEmail() + " - " + e.getMessage());
            }
        }

        Map<String, List<String>> result = new HashMap<>();
        result.put("successes", successes);
        result.put("failures", failures);

        return result;
    }
}
