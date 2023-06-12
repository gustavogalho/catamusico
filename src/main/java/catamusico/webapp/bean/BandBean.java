package catamusico.webapp.bean;

import org.springframework.web.multipart.MultipartFile;

import catamusico.webapp.domain.Band;

public class BandBean {

	private Long id;
	private String city;
	private String state;
	private String musicGenre;
	private String contact;
	private String experiences;
	private MultipartFile[] files;

	public BandBean() {
	}

	public BandBean(Band band) {
		this.id = band.getId();
		this.city = band.getCity();
		this.state = band.getState();
		this.musicGenre = band.getMusicGenre();
		this.contact = band.getContact();
		this.experiences = band.getExperiences();
	}

	public BandBean(String city, String state, String musicGenre, String contact,
			String experiences,
			MultipartFile[] files) {
		this.city = city;
		this.state = state;
		this.musicGenre = musicGenre;
		this.contact = contact;
		this.experiences = experiences;
		this.files = files;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getExperiences() {
		return experiences;
	}

	public void setExperiences(String experiences) {
		this.experiences = experiences;
	}

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

}
