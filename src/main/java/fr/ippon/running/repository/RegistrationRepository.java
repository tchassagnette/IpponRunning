package fr.ippon.running.repository;

import java.util.List;

import fr.ippon.running.domain.Registration;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Registration entity.
 */
public interface RegistrationRepository extends
		JpaRepository<Registration, Long> {

	List<Registration> findRegistrationByLogin(String login);

	List<Registration> findRegistrationByLoginAndEventId(String login,
			Long eventId);

	List<Registration> findRegistrationByEventId(Long eventId);

}
