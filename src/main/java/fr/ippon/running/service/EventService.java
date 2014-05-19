package fr.ippon.running.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.ippon.running.domain.Event;
import fr.ippon.running.domain.Registration;
import fr.ippon.running.repository.EventRepository;
import fr.ippon.running.repository.RegistrationRepository;
import fr.ippon.running.web.rest.dto.EventDTO;

@Service
public class EventService {
	
	private EventRepository eventRepository;
	
	private RegistrationRepository registrationRepository;

	public List<EventDTO> getUpcomingEvents(String login){
		final List<Event> upComingEventsList = eventRepository.findUpComingEvents();
		final List<Registration> userRegistrationsList = registrationRepository.findRegistrationByLogin(login);
		final List<EventDTO> eventDTOList = new ArrayList<EventDTO>();
		for(Event event:upComingEventsList){
			final EventDTO eventDto = new EventDTO();
			eventDto.setUserRegistered(isUserRegistered(event,userRegistrationsList));
			eventDTOList.add(eventDto);
		}
		return eventDTOList;
	}
	
	private boolean isUserRegistered(Event event, List<Registration> userRegistrationsList){
		return false;
	}
}
