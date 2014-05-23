package fr.ippon.running.service;

import java.util.List;

import javax.inject.Inject;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import fr.ippon.running.domain.Registration;
import fr.ippon.running.repository.RegistrationRepository;

@Service
public class RegistrationService {

	@Inject
	private RegistrationRepository registrationRepository;

	public void registerToEvent(Long eventId, String login) {
		// On vérifie que l'utilisateur n'est pas déjà inscrit.
		final List<Registration> existingRegistrations = registrationRepository
				.findRegistrationByLoginAndEventId(login, eventId);
		if (existingRegistrations.size() == 0) {
			// On cree l'inscription
			final Registration registration = new Registration();
			registration.setLogin(login);
			registration.setEventId(eventId);
			registration.setRegistrationDate(new LocalDate());
			registrationRepository.save(registration);
		} else {
			// Erreur
		}

	}

	public void unregisterToEvent(Long eventId, String login) {
		final List<Registration> existingRegistrations = registrationRepository
				.findRegistrationByLoginAndEventId(login, eventId);
		registrationRepository.delete(existingRegistrations);

	}

}
