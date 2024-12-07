package com.klef.jfsd.sdp.DTO;

public class MonthlyReportDTO {
    private String month;
    private long achievements;
    private long participation;

    public MonthlyReportDTO(String month, long achievements, long participation) {
        this.month = month;
        this.achievements = achievements;
        this.participation = participation;
    }

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public long getAchievements() {
		return achievements;
	}

	public void setAchievements(long achievements) {
		this.achievements = achievements;
	}

	public long getParticipation() {
		return participation;
	}

	public void setParticipation(long participation) {
		this.participation = participation;
	}

    // Getters and setters
}
