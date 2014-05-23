package fr.ippon.running.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ippon.running.domain.Event;

/**
 * Spring Data JPA repository for the Event entity.
 */
public interface EventRepository extends JpaRepository<Event, Long> {

	@Query(value = "Select e from Event e where start_date > CURRENT_DATE")
	public List<Event> findUpComingEvents();
}
