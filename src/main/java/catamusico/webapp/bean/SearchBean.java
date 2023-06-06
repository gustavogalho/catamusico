package catamusico.webapp.bean;

public class SearchBean {
    private String city;
    private String state;
    private String instrument;
    private String experienceLevel;

    public SearchBean() {
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

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    @Override
    public String toString() {
        return "SearchBean [city=" + city + ", state=" + state + ", instrument=" + instrument + ", experienceLevel="
                + experienceLevel + "]";
    }

}
