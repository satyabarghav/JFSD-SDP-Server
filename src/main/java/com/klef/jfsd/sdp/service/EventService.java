package com.klef.jfsd.sdp.service;

import java.util.List;
import java.util.UUID;

import com.klef.jfsd.sdp.DTO.CreateEventRequest;
import com.klef.jfsd.sdp.DTO.CreateEventResponse;
import com.klef.jfsd.sdp.DTO.DailyEventReportDTO;
import com.klef.jfsd.sdp.DTO.EventWithStatusDTO;
import com.klef.jfsd.sdp.models.Event;


public interface EventService {
	CreateEventResponse createEvent(CreateEventRequest createEventRequest);
	List<Event> getAllEvents();
	Event getEventByID(int eventID);
	boolean deleteEvent(int eventID);
	public List<Event> getEventsRegisteredByStudent(UUID studentId);
	public List<Event> getEventsByTeacher(UUID teacherId);

	List<EventWithStatusDTO> getEventsWithParticipationStatus(UUID studentId);
//	List<DailyEventReportDTO> getDailyEventCounts();

}
