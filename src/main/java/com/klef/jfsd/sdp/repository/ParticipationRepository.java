package com.klef.jfsd.sdp.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.sdp.models.Event;
import com.klef.jfsd.sdp.models.Participation;
import com.klef.jfsd.sdp.models.Student;

import jakarta.transaction.Transactional;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Integer> {

	List<Participation> findByStudentId(UUID studentId);

	List<Participation> findByEventId(int eventId);

	List<Participation> findByStatus(String status);

	@Transactional
	@Modifying
	@Query("DELETE FROM Participation p WHERE p.student.id = ?1 AND p.event.id = ?2")
	void cancelRegistration(UUID studentId, int eventId);

	// Check for existing participation by event ID and student ID
	@Query("SELECT p FROM Participation p WHERE p.event.id = ?1 AND p.student.id = ?2")
	Optional<Participation> findByEventIdAndStudentId(int eventId, UUID studentId);

	// Check for unique participation by event and student objects
	Optional<Participation> findByEventAndStudent(Event event, Student student);
	
	@Query("SELECT p.student FROM Participation p WHERE p.event.id = :eventId AND p.isWinner = true")
    List<Student> findWinnersByEventId(@Param("eventId") int eventId);
}
