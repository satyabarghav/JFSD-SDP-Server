package com.klef.jfsd.sdp.service;

import java.util.List;

import com.klef.jfsd.sdp.DTO.AchievementUpdateDTO;
import com.klef.jfsd.sdp.DTO.WinnerDTO;
import com.klef.jfsd.sdp.models.Achievement;

public interface AchievementService {
	void recordWinnersForEvent(int eventId, List<WinnerDTO> winners);

	
	public List<Achievement> getWinnersForEvent(int eventId);


	Achievement getAchievementById(int achievementId);


	void updateAchievement(int achievementId, AchievementUpdateDTO updates);

}
