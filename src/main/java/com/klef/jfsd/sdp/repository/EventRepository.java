package com.klef.jfsd.sdp.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.sdp.DTO.DailyEventReportDTO;
import com.klef.jfsd.sdp.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
	List<Event> findByEventType(String eventType);

	List<Event> findByClassification(String classification);

	@Query("SELECT e FROM Event e JOIN e.participations p WHERE p.student.id = ?1")
	List<Event> findEventsRegisteredByStudent(UUID studentId);

	List<Event> findByInchargeId(UUID teacherId);

	@Query("SELECT e, p.status FROM Event e LEFT JOIN Participation p ON e.id = p.event.id AND p.student.id = :studentId")
	List<Object[]> findEventsWithParticipationStatusByStudentId(@Param("studentId") UUID studentId);

//	@Query(value = "SELECT DATE(e.event_date) as date, COUNT(e.id) as eventCount " + "FROM event_table e "
//			+ "GROUP BY DATE(e.event_date) " + "ORDER BY DATE(e.event_date)", nativeQuery = true)
//	List<Object[]> getDailyEventCounts();

}
