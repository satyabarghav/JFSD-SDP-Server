package com.klef.jfsd.sdp.DTO;

import java.time.LocalDate;

import com.klef.jfsd.sdp.models.ParticipationStatus;

public class ParticipationRequest {
    private String regNum;
    private int eventId;
    private LocalDate participationDate;
    private ParticipationStatus status;
	public String getRegNum() {
		return regNum;
	}
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public LocalDate getParticipationDate() {
		return participationDate;
	}
	public void setParticipationDate(LocalDate participationDate) {
		this.participationDate = participationDate;
	}
	public ParticipationStatus getStatus() {
		return status;
	}
	public void setStatus(ParticipationStatus status) {
		this.status = status;
	}
	

    // Getters and Setters
}
