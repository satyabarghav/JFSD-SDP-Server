package com.klef.jfsd.sdp.DTO;

import java.time.LocalDateTime;

public class EventWithStatusDTO {
    private int eventId;
    private String title;
    private LocalDateTime eventDate;
    private String location;
    private String status;
    private int maxParticipants;
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDateTime getEventDate() {
		return eventDate;
	}
	public void setEventDate(LocalDateTime localDateTime) {
		this.eventDate = localDateTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getMaxParticipants() {
		return maxParticipants;
	}
	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

    // Getters and Setters
}
