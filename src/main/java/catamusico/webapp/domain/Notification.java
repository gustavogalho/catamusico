package catamusico.webapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Notification {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    @ManyToOne
    private Musician musician;

    @ManyToOne
    private Band band;
    private boolean isRead;


    public Notification() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Musician getMusician() {
        return musician;
    }

    public void setMusician(Musician musician) {
        this.musician = musician;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public boolean getRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        this.isRead = read;
    }
}
