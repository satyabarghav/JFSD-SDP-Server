package com.klef.jfsd.sdp.DTO;

import java.util.UUID;
import com.klef.jfsd.sdp.models.AchievementType;

public class WinnerDTO {
    private UUID studentId;
    private String rank;
    private UUID teacherId;
    private AchievementType achievementType;

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

    public AchievementType getAchievementType() {
        return achievementType;
    }

    public void setAchievementType(AchievementType achievementType) {
        this.achievementType = achievementType;
    }
}
