package fr.ippon.running.web.rest;

import com.codahale.metrics.annotation.Timed;

import fr.ippon.running.domain.Event;
import fr.ippon.running.repository.EventRepository;
import fr.ippon.running.security.SecurityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * REST controller for managing Event.
 */
@RestController
@RequestMapping("/app")
public class EventResource {

    private final Logger log = LoggerFactory.getLogger(EventResource.class);

    @Inject
    private EventRepository eventRepository;

    /**
     * POST  /rest/events -> Create a new event.
     */
    @RequestMapping(value = "/rest/events",
            method = RequestMethod.POST,
            produces = "application/json")
    @Timed
    public void create(@RequestBody Event event) {
        log.debug("REST request to save Event : {}", event);
        eventRepository.save(event);
    }

    /**
     * GET  /rest/events -> get all the events.
     */
    @RequestMapping(value = "/rest/events",
            method = RequestMethod.GET,
            produces = "application/json")
    @Timed
    public List<Event> getAll() {
    	final String login = SecurityUtils.getCurrentLogin();
        log.debug("REST request to get all Events");
        return eventRepository.findAll();
    }

    /**
     * GET  /rest/events/:id -> get the "id" event.
     */
    @RequestMapping(value = "/rest/events/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
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
     * DELETE  /rest/events/:id -> delete the "id" event.
     */
    @RequestMapping(value = "/rest/events/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    @Timed
    public void delete(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to delete Event : {}", id);
        eventRepository.delete(id);
    }
}