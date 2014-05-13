package fr.ippon.running.repository;

import fr.ippon.running.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Result entity.
 */
public interface ResultRepository extends JpaRepository<Result, Long> {

}
