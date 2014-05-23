package fr.ippon.running.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.ippon.running.domain.Event;
import fr.ippon.running.domain.Registration;
import fr.ippon.running.domain.User;
import fr.ippon.running.repository.EventRepository;
import fr.ippon.running.repository.RegistrationRepository;
import fr.ippon.running.repository.UserRepository;
import fr.ippon.running.web.rest.dto.EventDTO;
import fr.ippon.running.web.rest.dto.UserDTO;

@Service
public class EventService {

	@Inject
	private EventRepository eventRepository;
	@Inject
	private RegistrationRepository registrationRepository;
	@Inject
	private UserRepository userRepository;

	@Transactional
	public EventDTO getEventDetail(Long id, String login) {
		final Event event = eventRepository.getOne(id);
		final List<Registration> userRegistrationsList = registrationRepository
				.findRegistrationByLogin(login);
		final EventDTO eventDto = new EventDTO(event);
		eventDto.setUserRegistered(isUserRegistered(event,
				userRegistrationsList));
		return eventDto;
	}

	/**
	 * Retourne la liste des événements à venir avec l'information d'inscription
	 * ou non d'un utilisateur.
	 * 
	 * @param login
	 *            Login de l'utilisateur.
	 * @return
	 */
	public List<EventDTO> getUpcomingEvents(String login) {
		final List<Event> upComingEventsList = eventRepository
				.findUpComingEvents();
		final List<Registration> userRegistrationsList = registrationRepository
				.findRegistrationByLogin(login);
		final List<EventDTO> eventDTOList = new ArrayList<EventDTO>();
		for (Event event : upComingEventsList) {
			final EventDTO eventDto = new EventDTO(event);
			eventDto.setUserRegistered(isUserRegistered(event,
					userRegistrationsList));
			eventDTOList.add(eventDto);
		}
		return eventDTOList;
	}

	private boolean isUserRegistered(Event event,
			List<Registration> userRegistrationsList) {
		for (Registration registration : userRegistrationsList) {
			if (registration.getEventId() == event.getId()) {
				return true;
			}
		}
		return false;
	}

	public List<User> getRegisteredUsers(Long eventId) {
		final List<Registration> eventRegistrations = registrationRepository
				.findRegistrationByEventId(eventId);
		final Set<String> registeredLogins = new HashSet<String>();
		for (Registration registration : eventRegistrations) {
			registeredLogins.add(registration.getLogin());
		}
		return userRepository.findAll(registeredLogins);
	}
}
