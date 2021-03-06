package fr.ippon.running.service;

import java.util.List;

import javax.inject.Inject;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.ippon.running.domain.PersistentToken;
import fr.ippon.running.domain.User;
import fr.ippon.running.domain.UserInfo;
import fr.ippon.running.repository.PersistentTokenRepository;
import fr.ippon.running.repository.UserInfoRepository;
import fr.ippon.running.repository.UserRepository;
import fr.ippon.running.security.SecurityUtils;
import fr.ippon.running.web.rest.dto.UserInfoDTO;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

	private final Logger log = LoggerFactory.getLogger(UserService.class);

	@Inject
	private PasswordEncoder passwordEncoder;

	@Inject
	private UserRepository userRepository;

	@Inject
	private UserInfoRepository userInfoRepository;

	@Inject
	private PersistentTokenRepository persistentTokenRepository;

	public void updateUserInformation(String firstName, String lastName,
			String email) {
		User currentUser = userRepository.findOne(SecurityUtils
				.getCurrentLogin());
		currentUser.setFirstName(firstName);
		currentUser.setLastName(lastName);
		currentUser.setEmail(email);
		userRepository.save(currentUser);
		log.debug("Changed Information for User: {}", currentUser);
	}

	public void changePassword(String password) {
		User currentUser = userRepository.findOne(SecurityUtils
				.getCurrentLogin());
		String encryptedPassword = passwordEncoder.encode(password);
		currentUser.setPassword(encryptedPassword);
		userRepository.save(currentUser);
		log.debug("Changed password for User: {}", currentUser);
	}

	@Transactional(readOnly = true)
	public User getUserWithAuthorities() {
		User currentUser = userRepository.findOne(SecurityUtils
				.getCurrentLogin());
		currentUser.getAuthorities().size(); // eagerly load the association
		return currentUser;
	}

	/**
	 * Persistent Token are used for providing automatic authentication, they
	 * should be automatically deleted after 30 days.
	 * <p/>
	 * <p>
	 * This is scheduled to get fired everyday, at midnight.
	 * </p>
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void removeOldPersistentTokens() {
		LocalDate now = new LocalDate();
		List<PersistentToken> tokens = persistentTokenRepository
				.findByTokenDateBefore(now.minusMonths(1));
		for (PersistentToken token : tokens) {
			log.debug("Deleting token {}", token.getSeries());
			User user = token.getUser();
			user.getPersistentTokens().remove(token);
			persistentTokenRepository.delete(token);
		}
	}

	public UserInfoDTO getUserInfos(String login) {
		// On vérifie que le user existe
		if (userRepository.exists(login)) {
			final UserInfo userInfo = userInfoRepository.findByLogin(login);
			return new UserInfoDTO(userInfo);
		}
		return null;
	}
}
