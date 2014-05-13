package fr.ippon.running.repository;

import fr.ippon.running.domain.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Registration entity.
 */
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

}
