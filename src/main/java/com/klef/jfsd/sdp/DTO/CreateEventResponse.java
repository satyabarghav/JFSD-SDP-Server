package com.klef.jfsd.sdp.DTO;

import java.time.LocalDateTime;

public class CreateEventResponse {
    private int eventId;
    private String title;
    private String message;
    private boolean success;
    private LocalDateTime date;

    // Constructor
    public CreateEventResponse(int eventId, String title, String message, boolean success,LocalDateTime date) {
        this.eventId = eventId;
        this.title = title;
        this.message = message;
        this.success = success;
        this.date = date;
    }
    
    public CreateEventResponse(int eventId, String title, String message, boolean success) {
        this.eventId = eventId;
        this.title = title;
        this.message = message;
        this.success = success;
    }

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

    // Getters and Setters
    // Add all getters and setters here
}