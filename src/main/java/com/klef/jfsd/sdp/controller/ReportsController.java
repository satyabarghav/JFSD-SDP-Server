//package com.klef.jfsd.sdp.controller;
//
//import com.klef.jfsd.sdp.DTO.CategoryReportDTO;
//import com.klef.jfsd.sdp.DTO.DailyEventReportDTO;
//import com.klef.jfsd.sdp.DTO.MonthlyReportDTO;
//import com.klef.jfsd.sdp.repository.AchievementRepository;
//import com.klef.jfsd.sdp.repository.EventRepository;
//import com.klef.jfsd.sdp.service.EventService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/reports")
//public class ReportsController {
//
//    @Autowired
//    private AchievementRepository achievementRepository;
//
//    @Autowired
//    private EventService eventService;
//
//    // Endpoint for monthly achievements and participation
//    @GetMapping("/monthly")
//    public ResponseEntity<List<MonthlyReportDTO>> getMonthlyReports() {
//        try {
//            List<MonthlyReportDTO> reports = achievementRepository.getMonthlyReports();
//            return ResponseEntity.ok(reports);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }
//
//    // Endpoint for achievements by category
//    @GetMapping("/categories")
//    public ResponseEntity<List<CategoryReportDTO>> getAchievementsByCategory() {
//        try {
//            List<CategoryReportDTO> reports = achievementRepository.getAchievementsByCategory();
//            return ResponseEntity.ok(reports);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }
//
//    @GetMapping("/events/daily")
//    public ResponseEntity<List<DailyEventReportDTO>> getDailyEventCounts() {
//        try {
//            List<DailyEventReportDTO> reports = eventService.getDailyEventCounts();
//            return ResponseEntity.ok(reports);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }
//}
