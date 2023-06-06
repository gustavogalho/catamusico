package catamusico.webapp.bean;

import org.springframework.web.multipart.MultipartFile;

public class RegisterBean {

	private String name;
	private String city;
	private String state;
	private String musicGenre;
	private String contact;
	private String instrument;
	private String experiences;
	private String experienceLevel;
	private String email;
	private String password;
	private MultipartFile[] files;

	public RegisterBean() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "RegisterBean [name=" + name + ", city=" + city + ", state=" + state + ", musicGenre=" + musicGenre + ", contact=" + contact + ", instrument="
				+ instrument + ", experiences=" + experiences + ", experienceLevel=" + experienceLevel + ", email=" + email + ", password=" + password + "]";
	}

}
