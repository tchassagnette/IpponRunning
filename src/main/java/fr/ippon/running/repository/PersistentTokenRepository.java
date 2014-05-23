package fr.ippon.running.repository;

import fr.ippon.running.domain.PersistentToken;
import fr.ippon.running.domain.User;
import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface PersistentTokenRepository extends
		JpaRepository<PersistentToken, String> {

	List<PersistentToken> findByUser(User user);

	List<PersistentToken> findByTokenDateBefore(LocalDate localDate);

}
