package com.klef.jfsd.sdp.models;

public enum ParticipationStatus {
	REGISTERED,   // Signed up but not yet attended
    ATTENDED,     // Attended but no particular outcome
    COMPLETED,    // Fully participated and finished
    ACHIEVED,     // Participated and earned recognition
    NOT_ATTENDED, // Registered but didn’t show up
    WITHDRAWN   
}
