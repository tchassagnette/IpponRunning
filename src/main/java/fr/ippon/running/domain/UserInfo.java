package fr.ippon.running.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;

import fr.ippon.running.domain.util.CustomLocalDateSerializer;

@Entity
@Table(name = "T_USER_INFOS")
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Size(min = 1, max = 100)
	@Column(name = "adresse")
	private String adresse;

	@Size(min = 1, max = 100)
	@Column(name = "postal_code")
	private String postalCode;

	@Size(min = 1, max = 100)
	@Column(name = "city")
	private String city;

	@Size(min = 1, max = 100)
	@Column(name = "nationality")
	private String nationality;

	@Size(min = 1, max = 100)
	@Column(name = "contact")
	private String contact;

	@Size(min = 1, max = 100)
	@Column(name = "contact_phone")
	private String contactPhone;

	@Size(min = 1, max = 100)
	@Column(name = "tshirt_size")
	private String tshirtSize;

	@Size(min = 1, max = 100)
	@Column(name = "licence_type")
	private String licenceType;

	@Size(min = 1, max = 100)
	@Column(name = "licence_number")
	private String licenceNumber;

	@NotNull
	@Column(name = "update_date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	private LocalDate updateDate;

	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "user_login")
	private String login;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getTshirtSize() {
		return tshirtSize;
	}

	public void setTshirtSize(String tshirtSize) {
		this.tshirtSize = tshirtSize;
	}

	public String getLicenceType() {
		return licenceType;
	}

	public void setLicenceType(String licenceType) {
		this.licenceType = licenceType;
	}

	public String getLicenceNumber() {
		return licenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
