package fr.ippon.running.web.rest;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import fr.ippon.running.domain.User;
import fr.ippon.running.repository.UserRepository;
import fr.ippon.running.security.AuthoritiesConstants;
import fr.ippon.running.service.UserService;
import fr.ippon.running.web.rest.dto.UserInfoDTO;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/app")
public class UserResource {

	private final Logger log = LoggerFactory.getLogger(UserResource.class);

	@Inject
	private UserRepository userRepository;

	@Inject
	private UserService userService;

	/**
	 * GET /rest/users/:login -> get the "login" user.
	 */
	@RequestMapping(value = "/rest/users/{login}/", method = RequestMethod.GET, produces = "application/json")
	@Timed
	@RolesAllowed(AuthoritiesConstants.ADMIN)
	public User getUser(@PathVariable String login, HttpServletResponse response) {
		log.debug("REST request to get User : {}", login);
		User user = userRepository.findOne(login);
		if (user == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return user;
	}

	/**
	 * GET /rest/users/:login -> get the "login" user.
	 */
	@RequestMapping(value = "/rest/users/{login}/infos", method = RequestMethod.GET, produces = "application/json")
	@Timed
	@RolesAllowed(AuthoritiesConstants.ADMIN)
	public UserInfoDTO getUserInfos(@PathVariable String login,
			HttpServletResponse response) {
		log.debug("REST request to get User : {}", login);
		return userService.getUserInfos(login);
	}

	public void saveUserInfos() {

	}
}
