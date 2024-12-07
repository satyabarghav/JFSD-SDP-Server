package com.klef.jfsd.sdp.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "event_table")
public class Event {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @Column(nullable = false, length = 100)
	    private String title;

	    @Column(nullable = false)
	    private String description;

	    @Column(nullable = false)
	    private LocalDateTime eventDate;

	    @Column(nullable = false, length = 100)
	    private String location;

	    @Column(nullable = false)
	    private int maxParticipants;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private EventType eventType;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private EventClassification classification;

	    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JsonManagedReference // Prevents infinite recursion
	    private List<Participation> participations;


	    @ManyToOne
	    @JoinColumn(name = "teacher_id", nullable = false)
	    private Teacher incharge;


    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", title=" + title + ", description=" + description + 
               ", eventDate=" + eventDate + ", location=" + location + 
               ", maxParticipants=" + maxParticipants + ", eventType=" + eventType + 
               ", classification=" + classification + ", participations=" + participations + "]";
    }

	public Teacher getIncharge() {
		return incharge;
	}

	public void setIncharge(Teacher incharge) {
		this.incharge = incharge;
	}

	public boolean isCompetitive() {
		if(classification.equals(EventClassification.COMPETITIVE))
			return true;
		return false;
	}
}
