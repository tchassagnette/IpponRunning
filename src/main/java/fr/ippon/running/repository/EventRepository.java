package fr.ippon.running.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ippon.running.domain.Event;

/**
 * Spring Data JPA repository for the Event entity.
 */
public interface EventRepository extends JpaRepository<Event, Long> {
	

}
