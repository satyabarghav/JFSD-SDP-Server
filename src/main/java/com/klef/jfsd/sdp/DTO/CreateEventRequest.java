package com.klef.jfsd.sdp.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

import com.klef.jfsd.sdp.models.EventClassification;
import com.klef.jfsd.sdp.models.EventType;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateEventRequest {
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Event date is required")
    @Future(message = "Event date must be in the future")
    private LocalDateTime eventDate;

    @NotBlank(message = "Location is required")
    private String location;

    @Min(value = 1, message = "Maximum participants must be at least 1")
    private int maxParticipants;

    @NotNull(message = "Event type is required")
    private EventType eventType;

    @NotNull(message = "Event classification is required")
    private EventClassification classification;

    @NotNull(message = "Teacher ID is required")
    private UUID teacherId; // Added field for the teacher ID

    // Default constructor
    public CreateEventRequest() {
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public EventClassification getClassification() {
        return classification;
    }

    public void setClassification(EventClassification classification) {
        this.classification = classification;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "CreateEventRequest [title=" + title + ", description=" + description + 
               ", eventDate=" + eventDate + ", location=" + location + 
               ", maxParticipants=" + maxParticipants + 
               ", eventType=" + eventType + ", classification=" + classification + 
               ", teacherId=" + teacherId + "]";
    }
}
