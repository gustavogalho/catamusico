package catamusico.webapp.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import catamusico.webapp.bean.RegisterBean;

@Entity
public class Band {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String city;
	private String state;
	private String musicGenre;
	private String contact;
	private String experiences;
	@OneToOne
	private Login login;
	@ManyToMany
	@JoinTable(name = "band_musician", // Nome da tabela de junção
			joinColumns = @JoinColumn(name = "band_id"), // Nome da coluna que referencia a banda
			inverseJoinColumns = @JoinColumn(name = "musician_id") // Nome da coluna que referencia o músico
	)
	private List<Musician> favorites;
	@OneToMany
	private List<File> media;

	public Band() {

	}

	public Band(RegisterBean registerBean, Login login, List<File> fileList) {
		this.name = registerBean.getName();
		this.city = registerBean.getCity();
		this.state = registerBean.getState().split("-")[1];
		this.musicGenre = registerBean.getMusicGenre();
		this.contact = registerBean.getContact();
		this.login = login;
		this.media = fileList;
		this.experiences = registerBean.getExperiences();
	}

	public Band(String name, String city, String state, String musicGenre, String contact, String experiences,
			Login login, List<Musician> favorites, List<File> media) {
		this.name = name;
		this.city = city;
		this.state = state;
		this.musicGenre = musicGenre;
		this.contact = contact;
		this.experiences = experiences;
		this.login = login;
		this.favorites = favorites;
		this.media = media;
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

	public String getExperiences() {
		return experiences;
	}

	public void setExperiences(String experiences) {
		this.experiences = experiences;
	}

	public List<File> getMedia() {
		return media;
	}

	public void setMedia(List<File> media) {
		this.media = media;
	}

	@Override
	public String toString() {
		return "Band [id=" + id + ", name=" + name + ", city=" + city + ", state=" + state + ", musicGenre="
				+ musicGenre + ", contact=" + contact + ", experiences=" + experiences + ", login=" + login
				+ ", favorites=" + favorites + ", media=" + media + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Band band = (Band) o;
		return Objects.equals(id, band.id);
	}

}
