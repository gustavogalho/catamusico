package catamusico.webapp.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import catamusico.webapp.bean.RegisterBean;

@Entity
public class Band {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String city;
	private String state;
	private String musicGenre;
	private String contact;
	@OneToOne
	private Login login;
	@OneToMany
	private List<Musician> favorites;

	public Band() {

	}

	public Band(RegisterBean registerBean, Login login) {
		this.name = registerBean.getName();
		this.city = registerBean.getCity();
		this.state = registerBean.getState().split("-")[1];
		this.musicGenre = registerBean.getMusicGenre();
		this.contact = registerBean.getContact();
		this.login = login;
	}

	public Band(String name, String city, String state, String musicGenre, String contact, List<Musician> favorites) {
		this.name = name;
		this.city = city;
		this.state = state;
		this.musicGenre = musicGenre;
		this.contact = contact;
		this.favorites = favorites;
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

	public List<Musician> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Musician> favorites) {
		this.favorites = favorites;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "Band [id=" + id + ", name=" + name + ", city=" + city + ", state=" + state + ", musicGenre=" + musicGenre + ", contact=" + contact + ", login="
				+ login + ", favorites=" + favorites + "]";
	}

}
