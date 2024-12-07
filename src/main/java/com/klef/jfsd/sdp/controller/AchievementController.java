package com.klef.jfsd.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.klef.jfsd.sdp.DTO.AchievementUpdateDTO;
import com.klef.jfsd.sdp.DTO.WinnerDTO;
import com.klef.jfsd.sdp.models.Achievement;
import com.klef.jfsd.sdp.service.AchievementService;

@RestController
@RequestMapping("/api/achievements")
public class AchievementController {

    @Autowired
    private AchievementService achievementService;

    @PostMapping("/{eventId}/record-winners")
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public ResponseEntity<String> recordWinners(
            @PathVariable int eventId,
            @RequestBody List<WinnerDTO> winners) {

        try {
            achievementService.recordWinnersForEvent(eventId, winners);
            return ResponseEntity.ok("Winners recorded successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error recording winners: " + e.getMessage());
        }
    }

    @GetMapping("/{eventId}/winners")
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public ResponseEntity<List<Achievement>> getWinnersForEvent(@PathVariable int eventId) {
        try {
            return ResponseEntity.ok(achievementService.getWinnersForEvent(eventId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PatchMapping("/{achievementId}/update")
    @PreAuthorize("hasRole('STUDENT') or hasRole('TEACHER')")
    public ResponseEntity<String> updateAchievement(
            @PathVariable int achievementId,
            @RequestBody AchievementUpdateDTO updates) {

        try {
            achievementService.updateAchievement(achievementId, updates);
            return ResponseEntity.ok("Achievement updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating achievement: " + e.getMessage());
        }
    }

    @GetMapping("/{achievementId}")
    @PreAuthorize("hasRole('STUDENT') or hasRole('TEACHER')")
    public ResponseEntity<Achievement> getAchievementById(@PathVariable int achievementId) {
        try {
            return ResponseEntity.ok(achievementService.getAchievementById(achievementId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
