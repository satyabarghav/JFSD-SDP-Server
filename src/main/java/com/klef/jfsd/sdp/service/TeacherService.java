package com.klef.jfsd.sdp.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.sdp.DTO.TeacherSignUpRequest;
import com.klef.jfsd.sdp.DTO.TeacherSignUpResponse;
import com.klef.jfsd.sdp.models.Teacher;

public interface TeacherService {
    TeacherSignUpResponse registerTeacher(TeacherSignUpRequest teacherSignUpRequest);
    List<Teacher> getAllTeachers();
    boolean deleteTeacher(UUID id);
    Map<String, List<String>> registerTeachersBatch(MultipartFile file) throws IOException;

}
