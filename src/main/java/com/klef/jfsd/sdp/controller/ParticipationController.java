package com.klef.jfsd.sdp.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.jfsd.sdp.DTO.AttendanceUpdateRequest;
import com.klef.jfsd.sdp.DTO.ParticipationRequest;
import com.klef.jfsd.sdp.models.Participation;
import com.klef.jfsd.sdp.models.Student;
import com.klef.jfsd.sdp.service.ParticipationService;

@RestController
@RequestMapping("api/participations")
public class ParticipationController {

    @Autowired
    private ParticipationService participationService;

    // Register a student for an event
    @PostMapping("/register")
    public ResponseEntity<String> registerStudentForParticipation(@RequestBody ParticipationRequest request) {
    	System.out.println(request.getParticipationDate());
        return ResponseEntity.ok(participationService.registerStudentForParticipation(request));
    }

    // Cancel registration for a specific student and event
    @DeleteMapping("/{eventId}/cancel-registration")
    public ResponseEntity<String> cancelEventRegistration(@PathVariable int eventId, @RequestParam UUID studentId) {
        if (participationService.cancelEventRegistration(studentId, eventId)) {
            return ResponseEntity.ok("Cancelled Event Registration Successfully!");
        }
        return ResponseEntity.status(404).body("Could Not Cancel Your Registration, Try Again Later!");
    }

    // Update attendance for students in an event
    @PostMapping("/{eventId}/attendance")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<String> updateAttendance(
            @PathVariable int eventId,
            @RequestBody List<AttendanceUpdateRequest> attendanceUpdates) {

        boolean success = participationService.updateAttendanceBatch(eventId, attendanceUpdates);

        if (success) {
            return ResponseEntity.ok("Attendance updated successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event or Students not found");
        }
    }

    // Fetch participation details for a specific event
    @GetMapping("/events/{eventId}/participations")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<List<Participation>> getParticipationsForEvent(@PathVariable int eventId) {
        List<Participation> participations = participationService.getParticipationsForEvent(eventId);
        if (participations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(participations);
        }
        return ResponseEntity.ok(participations);
    }

    // Fetch students registered for a specific event (Deprecated in favor of participations)
    @GetMapping("/events/{eventId}/students")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<List<Student>> getStudentsForEvent(@PathVariable int eventId) {
        List<Student> students = participationService.getStudentsByEventId(eventId);
        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(students);
        }
        return ResponseEntity.ok(students);
    }

}
