package com.klef.jfsd.sdp.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.sdp.DTO.AttendanceUpdateRequest;
import com.klef.jfsd.sdp.DTO.ParticipationRequest;
import com.klef.jfsd.sdp.models.Event;
import com.klef.jfsd.sdp.models.Participation;
import com.klef.jfsd.sdp.models.ParticipationStatus;
import com.klef.jfsd.sdp.models.Student;
import com.klef.jfsd.sdp.repository.EventRepository;
import com.klef.jfsd.sdp.repository.ParticipationRepository;
import com.klef.jfsd.sdp.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class ParticipationServiceImpl implements ParticipationService {

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public String registerStudentForEvent(UUID studentId, int eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        Optional<Student> student = studentRepository.findById(studentId);

        if (event.isEmpty() || student.isEmpty()) {
            throw new IllegalArgumentException("Event or Student not found");
        }

        // Check if the participation already exists
        Optional<Participation> existingParticipation = participationRepository.findByEventAndStudent(event.get(), student.get());
        if (existingParticipation.isPresent()) {
            throw new IllegalArgumentException("Student is already registered for this event");
        }

        // Create new participation entry
        Participation participation = new Participation();
        participation.setEvent(event.get());
        participation.setStudent(student.get());
        participation.setStatus(ParticipationStatus.REGISTERED); // Default status

        participationRepository.save(participation);
        return "Student successfully registered for the event!";
    }

    @Override
    public String registerStudentForParticipation(ParticipationRequest participationRequest) {
    	System.out.println(participationRequest.getRegNum());
        Optional<Event> event = eventRepository.findById(participationRequest.getEventId());
        Optional<Student> student = studentRepository.findByRegNum(participationRequest.getRegNum());

        if (event.isEmpty() || student.isEmpty()) {
            throw new IllegalArgumentException("Event or Student not found");
        }

        // Create a new participation record
        Participation participation = new Participation();
        participation.setEvent(event.get());
        participation.setStudent(student.get());
        participation.setStatus(ParticipationStatus.REGISTERED); // Default status
        participation.setParticipationDate(participationRequest.getParticipationDate());

        participationRepository.save(participation);
        return "Student participation registered successfully!";
    }

    @Override
    @Transactional
    public boolean cancelEventRegistration(UUID studentId, int eventId) {
        Optional<Participation> participation = participationRepository.findByEventIdAndStudentId(eventId, studentId);

        if (participation.isEmpty()) {
            return false;
        }

        participationRepository.cancelRegistration(studentId, eventId);
        return true;
    }

    @Override
    public boolean updateAttendanceBatch(int eventId, List<AttendanceUpdateRequest> attendanceUpdates) {
        boolean allUpdatesSuccessful = true;

        for (AttendanceUpdateRequest update : attendanceUpdates) {
            Optional<Participation> participation = participationRepository.findByEventIdAndStudentId(eventId, update.getStudentId());

            if (participation.isPresent()) {
                Participation p = participation.get();
                p.setStatus(update.getIsPresent() ? ParticipationStatus.ATTENDED : ParticipationStatus.NOT_ATTENDED);
                participationRepository.save(p);
            } else {
                allUpdatesSuccessful = false;
            }
        }

        return allUpdatesSuccessful;
    }

    @Override
    public List<Participation> getParticipationsForEvent(int eventId) {
        return participationRepository.findByEventId(eventId);
    }

    @Override
    public List<Student> getStudentsByEventId(int eventId) {
        return participationRepository.findByEventId(eventId).stream()
                .map(Participation::getStudent)
                .toList();
    }   
}
