package fr.ippon.running.web.rest.dto;

import java.util.Date;

public class EventDTO {

	private Long id;
	
	private String title;
	
	private Date startDate;
	
	private String localization;
	
	private String description;
	
	private String shortDescription;
	
	private boolean isUserRegistered;
	
	private Long idUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getLocalization() {
		return localization;
	}

	public void setLocalization(String localization) {
		this.localization = localization;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public boolean isUserRegistered() {
		return isUserRegistered;
	}

	public void setUserRegistered(boolean isUserRegistered) {
		this.isUserRegistered = isUserRegistered;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	
}
