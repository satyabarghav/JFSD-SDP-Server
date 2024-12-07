package com.klef.jfsd.sdp.service;

import java.util.List;
import java.util.UUID;

import com.klef.jfsd.sdp.DTO.AttendanceUpdateRequest;
import com.klef.jfsd.sdp.DTO.ParticipationRequest;
import com.klef.jfsd.sdp.models.Participation;
import com.klef.jfsd.sdp.models.Student;

public interface ParticipationService {

    // Register a student for an event
    String registerStudentForEvent(UUID studentId, int eventId);

    // Register a student with custom participation request data (if needed)
    String registerStudentForParticipation(ParticipationRequest participationRequest);

    // Cancel registration for a specific student and event
    boolean cancelEventRegistration(UUID studentId, int eventId);

    // Update attendance for multiple students
    boolean updateAttendanceBatch(int eventId, List<AttendanceUpdateRequest> attendanceUpdates);

    // Fetch participation details (including status) for a specific event
    List<Participation> getParticipationsForEvent(int eventId);

    // Fetch student details (from participations) for a specific event
    List<Student> getStudentsByEventId(int eventId);
    
}
