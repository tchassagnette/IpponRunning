package fr.ippon.running.web.rest;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import fr.ippon.running.domain.Event;
import fr.ippon.running.domain.User;
import fr.ippon.running.repository.EventRepository;
import fr.ippon.running.security.SecurityUtils;
import fr.ippon.running.service.EventService;
import fr.ippon.running.service.RegistrationService;
import fr.ippon.running.web.rest.dto.EventDTO;

/**
 * REST controller for managing Event.
 */
@RestController
@RequestMapping("/app")
public class EventResource {

	private final Logger log = LoggerFactory.getLogger(EventResource.class);

	@Inject
	private EventRepository eventRepository;

	@Inject
	private EventService eventService;

	@Inject
	private RegistrationService registrationService;

	/**
	 * POST /rest/events -> Create a new event.
	 */
	@RequestMapping(value = "/rest/events", method = RequestMethod.POST, produces = "application/json")
	@Timed
	public void create(@RequestBody Event event) {
		log.debug("REST request to save Event : {}", event);
		eventRepository.save(event);
	}

	/**
	 * GET /rest/events -> get all the events.
	 */
	@RequestMapping(value = "/rest/events", method = RequestMethod.GET, produces = "application/json")
	@Timed
	public List<Event> getAll() {
		final String login = SecurityUtils.getCurrentLogin();
		log.debug("REST request to get all Events");
		return eventRepository.findAll();
	}

	/**
	 * GET /rest/events -> get all the events.
	 */
	@RequestMapping(value = "/rest/events/upcoming", method = RequestMethod.GET, produces = "application/json")
	@Timed
	public List<EventDTO> getUpcomingEvents() {
		final String login = SecurityUtils.getCurrentLogin();
		log.debug("REST request to get upcoming Events");
		return eventService.getUpcomingEvents(login);
	}

	@RequestMapping(value = "/rest/events/{id}/detail", method = RequestMethod.GET, produces = "application/json")
	@Timed
	public EventDTO getDetail(@PathVariable Long id,
			HttpServletResponse response) {
		log.debug("REST request to get Event Detail : {}", id);
		final String login = SecurityUtils.getCurrentLogin();
		EventDTO event = eventService.getEventDetail(id, login);
		if (event == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		log.info(event.toString());
		return event;
	}

	@RequestMapping(value = "/rest/events/{id}/details/registeredusers", method = RequestMethod.GET, produces = "application/json")
	@Timed
	public List<User> getRegisteredUsers(@PathVariable Long id,
			HttpServletResponse response) {
		log.debug("REST request to get registered users : {}", id);
		return eventService.getRegisteredUsers(id);
	}

	@RequestMapping(value = "/rest/events/{id}/register", method = RequestMethod.POST)
	@Timed
	public void register(@PathVariable Long id, HttpServletResponse response) {
		log.debug("REST request to register to an Event : {}", id);
		final String login = SecurityUtils.getCurrentLogin();
		registrationService.registerToEvent(id, login);
	}

	@RequestMapping(value = "/rest/events/{id}/register", method = RequestMethod.DELETE)
	@Timed
	public void unregister(@PathVariable Long id, HttpServletResponse response) {
		log.debug("REST request to unregister to an Event : {}", id);
		final String login = SecurityUtils.getCurrentLogin();
		registrationService.unregisterToEvent(id, login);
	}

	/**
	 * GET /rest/events/:id -> get the "id" event.
	 */
	@RequestMapping(value = "/rest/events/{id}", method = RequestMethod.GET, produces = "application/json")
	@Timed
	public Event get(@PathVariable Long id, HttpServletResponse response) {
		log.debug("REST request to get Event : {}", id);
		Event event = eventRepository.findOne(id);
		if (event == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		log.info(event.toString());
		return event;
	}

	/**
	 * DELETE /rest/events/:id -> delete the "id" event.
	 */
	@RequestMapping(value = "/rest/events/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@Timed
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		log.debug("REST request to delete Event : {}", id);
		eventRepository.delete(id);
	}
}
