package fr.ippon.running.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;

import fr.ippon.running.domain.util.CustomLocalDateSerializer;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

/**
 * A Event.
 */
@Entity
@Table(name = "T_EVENT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Event implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "title")
	private String title;

	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	@Column(name = "start_date")
	private LocalDate startDate;

	@Size(min = 1, max = 100)
	@Column(name = "localization")
	private String localization;

	@Size(min = 1, max = 1000)
	@Column(name = "description")
	private String description;

	@Size(min = 1, max = 100)
	@Column(name = "url_topo")
	private String urlTopo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
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

	public String getUrlTopo() {
		return urlTopo;
	}

	public void setUrlTopo(String urlTopo) {
		this.urlTopo = urlTopo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Event event = (Event) o;

		if (id != event.id) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
	public String toString() {
		return "User{" + "id='" + id + '\'' + "title='" + title + '\''
				+ "startDate='" + startDate + '\'' + "description='"
				+ description + '\'' + "}";
	}
}
