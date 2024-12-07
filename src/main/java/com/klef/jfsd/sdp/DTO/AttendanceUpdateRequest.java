package com.klef.jfsd.sdp.DTO;
import java.util.UUID;

public class AttendanceUpdateRequest {
    private UUID studentId;
    private boolean isPresent;

    // Getters and Setters
    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public boolean getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }
}
