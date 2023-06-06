package catamusico.webapp.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import catamusico.webapp.bean.RegisterBean;

@Entity
public class Musician {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String city;
	private String state;
	private String musicGenre;
	private String contact;
	private String instrument;
	private String experiences;
	private String experienceLevel;
	@OneToMany
	private List<File> media;
	@OneToOne
	private Login login;

	public Musician() {

	}

	public Musician(RegisterBean registerBean, Login login, List<File> fileList) {
		this.name = registerBean.getName();
		this.city = registerBean.getCity();
		this.state = registerBean.getState().split("-")[1];
		this.musicGenre = registerBean.getMusicGenre();
		this.contact = registerBean.getContact();
		this.instrument = registerBean.getInstrument();
		this.experiences = registerBean.getExperiences();
		this.experienceLevel = registerBean.getExperienceLevel();
		this.login = login;
		this.media = fileList;
	}

	public Musician(String name, String city, String state, String musicGenre, String contact, String instrument, String experiences, String experienceLevel) {
		this.name = name;
		this.city = city;
		this.state = state;
		this.musicGenre = musicGenre;
		this.contact = contact;
		this.instrument = instrument;
		this.experiences = experiences;
		this.experienceLevel = experienceLevel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMusicGenre() {
		return musicGenre;
	}

	public void setMusicGenre(String musicGenre) {
		this.musicGenre = musicGenre;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public String getExperiences() {
		return experiences;
	}

	public void setExperiences(String experiences) {
		this.experiences = experiences;
	}

	public String getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	public List<File> getMedia() {
		return media;
	}

	public void setMedia(List<File> media) {
		this.media = media;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "Musician [id=" + id + ", name=" + name + ", city=" + city + ", state=" + state + ", musicGenre=" + musicGenre + ", contact=" + contact
				+ ", instrument=" + instrument + ", experiences=" + experiences + ", experienceLevel=" + experienceLevel + ", login=" + login + "]";
	}

}
