package com.klef.jfsd.sdp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.sdp.DTO.AchievementUpdateDTO;
import com.klef.jfsd.sdp.DTO.WinnerDTO;
import com.klef.jfsd.sdp.models.Achievement;
import com.klef.jfsd.sdp.models.Event;
import com.klef.jfsd.sdp.models.Student;
import com.klef.jfsd.sdp.models.Teacher;
import com.klef.jfsd.sdp.repository.AchievementRepository;
import com.klef.jfsd.sdp.repository.EventRepository;
import com.klef.jfsd.sdp.repository.StudentRepository;
import com.klef.jfsd.sdp.repository.TeacherRepository;

@Service
public class AchievementServiceImpl implements AchievementService {

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void recordWinnersForEvent(int eventId, List<WinnerDTO> winners) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventId));

        for (WinnerDTO winner : winners) {
            Student student = studentRepository.findById(winner.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found with ID: " + winner.getStudentId()));

            Teacher teacher = teacherRepository.findById(winner.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + winner.getTeacherId()));

            Achievement achievement = new Achievement();
            achievement.setStudent(student);
            achievement.setTeacher(teacher);
            achievement.setEvent(event);
            achievement.setAchievementRank(winner.getRank());
            achievement.setStatus("APPROVED");
            achievement.setAchievementType(winner.getAchievementType()); // Assuming WinnerDTO has AchievementType
            achievement.setAchievementDate(LocalDate.now());

            achievementRepository.save(achievement);
        }
    }

    @Override
    public List<Achievement> getWinnersForEvent(int eventId) {
        return achievementRepository.findByEventIdAndAchievementRankIsNotNull(eventId);
    }

    @Override
    public void updateAchievement(int achievementId, AchievementUpdateDTO updates) {
        Achievement achievement = achievementRepository.findById(achievementId)
                .orElseThrow(() -> new RuntimeException("Achievement not found with ID: " + achievementId));

        if (updates.getTitle() != null) achievement.setTitle(updates.getTitle());
        if (updates.getDescription() != null) achievement.setDescription(updates.getDescription());
        if (updates.getCredentialUrl() != null) achievement.setCredentialUrl(updates.getCredentialUrl());
        if (updates.getStatus() != null) achievement.setStatus(updates.getStatus());
        if (updates.getFeedback() != null) achievement.setFeedback(updates.getFeedback());

        achievementRepository.save(achievement);
    }

    @Override
    public Achievement getAchievementById(int achievementId) {
        return achievementRepository.findById(achievementId)
                .orElseThrow(() -> new RuntimeException("Achievement not found with ID: " + achievementId));
    }
}
