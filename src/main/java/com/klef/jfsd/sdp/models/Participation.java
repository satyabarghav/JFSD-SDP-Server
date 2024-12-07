package com.klef.jfsd.sdp.models;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "participation_table")
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;  // Reference to the Event this participation is associated with

    @Column(nullable = false)
    private LocalDate participationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParticipationStatus status = ParticipationStatus.REGISTERED; // Default to REGISTERED
    
    @Column(nullable = false)
    private boolean isWinner = false; 

    // Getters and Setters
    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }

    public LocalDate getParticipationDate() { return participationDate; }
    public void setParticipationDate(LocalDate participationDate) { this.participationDate = participationDate; }

    public ParticipationStatus getStatus() { return status; }
    public void setStatus(ParticipationStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Participation [id=" + id + ", student=" + student + ", event=" + event + 
               ", participationDate=" + participationDate + ", status=" + status + "]";
    }
}
