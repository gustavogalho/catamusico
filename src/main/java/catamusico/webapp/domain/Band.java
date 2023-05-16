package catamusico.webapp.domain;

import javax.persistence.*;
import java.util.List;

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
    //@OneToOne
    //private Login login;
    @OneToMany
    private List<Musician> favorites;


    public Band() {

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
}
