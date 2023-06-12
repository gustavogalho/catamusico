package catamusico.webapp.bean;

import org.springframework.web.multipart.MultipartFile;

public class MusicianBean {

	private String city;
	private String state;
	private String musicGenre;
	private String contact;
	private String instrument;
	private String experiences;
	private String experienceLevel;
	private MultipartFile[] files;

	public MusicianBean() {
	}

	public MusicianBean(String city, String state, String musicGenre, String contact, String instrument, String experiences, String experienceLevel,
			MultipartFile[] files) {
		this.city = city;
		this.state = state;
		this.musicGenre = musicGenre;
		this.contact = contact;
		this.instrument = instrument;
		this.experiences = experiences;
		this.experienceLevel = experienceLevel;
		this.files = files;
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

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

}
