package com.klef.jfsd.sdp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.sdp.DTO.CreateEventRequest;
import com.klef.jfsd.sdp.DTO.CreateEventResponse;
import com.klef.jfsd.sdp.DTO.DailyEventReportDTO;
import com.klef.jfsd.sdp.DTO.EventWithStatusDTO;
import com.klef.jfsd.sdp.models.Event;
import com.klef.jfsd.sdp.models.ParticipationStatus;
import com.klef.jfsd.sdp.models.Teacher;
import com.klef.jfsd.sdp.repository.EventRepository;
import com.klef.jfsd.sdp.repository.TeacherRepository;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;

	public CreateEventResponse createEvent(CreateEventRequest createEventRequest) {
	    Optional<Teacher> teacher = teacherRepository.findById(createEventRequest.getTeacherId());

	    if (teacher.isEmpty()) {
	        throw new IllegalArgumentException("Teacher not found");
	    }

	    Event event = new Event();
	    event.setTitle(createEventRequest.getTitle());
	    event.setDescription(createEventRequest.getDescription());
	    event.setEventDate(createEventRequest.getEventDate());
	    event.setLocation(createEventRequest.getLocation());
	    event.setMaxParticipants(createEventRequest.getMaxParticipants());
	    event.setEventType(createEventRequest.getEventType());
	    event.setClassification(createEventRequest.getClassification());
	    event.setIncharge(teacher.get());

	    Event savedEvent = eventRepository.save(event);

	    return new CreateEventResponse(savedEvent.getId(), savedEvent.getTitle(), "Event created successfully", true,savedEvent.getEventDate());
	}

	@Override
	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}

	@Override
	public Event getEventByID(int eventID) {
		Optional<Event> event = eventRepository.findById(eventID);
		return event.orElse(null);
	}

	@Override
	public boolean deleteEvent(int eventID) {
		try {
            // Check if the event exists
            if (eventRepository.existsById(eventID)) {
                eventRepository.deleteById(eventID); // Delete the event
                return true; // Return true if deletion was successful
            } else {
                return false; // Return false if the event was not found
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during deletion
            return false; // Return false if an error occurs
        }
	}

	@Override
	public List<Event> getEventsRegisteredByStudent(UUID studentId) {
		return eventRepository.findEventsRegisteredByStudent(studentId);
	}

	@Override
	public List<Event> getEventsByTeacher(UUID teacherId) {
		return eventRepository.findByInchargeId(teacherId);
	}

	@Override
	public List<EventWithStatusDTO> getEventsWithParticipationStatus(UUID studentId) {
		List<Object[]> results = eventRepository.findEventsWithParticipationStatusByStudentId(studentId);

        return results.stream().map(result -> {
            Event event = (Event) result[0];
            ParticipationStatus status = (ParticipationStatus) result[1];

            EventWithStatusDTO dto = new EventWithStatusDTO();
            dto.setEventId(event.getId());
            dto.setTitle(event.getTitle());
            dto.setEventDate(event.getEventDate());
            dto.setLocation(event.getLocation());
            dto.setStatus(status != null ? status.name() : "NOT_REGISTERED");
            dto.setMaxParticipants(event.getMaxParticipants());
            return dto;
        }).collect(Collectors.toList());
	}
	
//	@Override
//	public List<DailyEventReportDTO> getDailyEventCounts() {
//        List<Object[]> rawData = eventRepository.getDailyEventCounts();
//        List<DailyEventReportDTO> result = new ArrayList<>();
//
//        for (Object[] row : rawData) {
//            result.add(new DailyEventReportDTO(
//                ((java.sql.Date) row[0]).toLocalDate(),  // Convert SQL date to LocalDate
//                ((Number) row[1]).longValue()           // Cast count
//            ));
//        }
//
//        return result;
//    }


}
