package com.klef.jfsd.sdp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.sdp.DTO.CategoryReportDTO;
import com.klef.jfsd.sdp.DTO.DailyEventReportDTO;
import com.klef.jfsd.sdp.DTO.MonthlyReportDTO;
import com.klef.jfsd.sdp.models.Achievement;
import com.klef.jfsd.sdp.models.Event;
import com.klef.jfsd.sdp.models.Student;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Integer> {

    @Query("SELECT a FROM Achievement a WHERE a.event.id = :eventId AND a.achievementType = 'COMPETITION_WINNER'")
    List<Achievement> findWinnersByEventId(@Param("eventId") int eventId);

    Optional<Achievement> findByEventAndStudent(Event event, Student student);

    List<Achievement> findByEventIdAndAchievementRankIsNotNull(int eventId);

    // Monthly Reports Query
//    @Query(value = "SELECT new com.klef.jfsd.sdp.DTO.MonthlyReportDTO(" +
//                   "DATE_FORMAT(a.achievement_date, '%M') as month, " +
//                   "COUNT(a.id) as achievements, " +
//                   "(SELECT COUNT(p.id) FROM participation_table p WHERE MONTH(p.participation_date) = MONTH(a.achievement_date)) as participation) " +
//                   "FROM achievement_table a " +
//                   "GROUP BY MONTH(a.achievement_date), DATE_FORMAT(a.achievement_date, '%M') " +
//                   "ORDER BY MONTH(a.achievement_date)", nativeQuery = true)
//    List<MonthlyReportDTO> getMonthlyReports();

    // Achievements by Category
//    @Query("SELECT new com.klef.jfsd.sdp.DTO.CategoryReportDTO(a.achievementType, COUNT(a.id)) " +
//           "FROM Achievement a " +
//           "GROUP BY a.achievementType")
//    List<CategoryReportDTO> getAchievementsByCategory();
    
    

}
