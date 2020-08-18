package fr.formasport.catalogue.models;

public class Formation {
    private String id_formation, title, duration, description;

    public Formation() {
    }

    /**
     *
     * @param id_formation
     * @param title
     * @param duration
     * @param description
     */
    public Formation(String id_formation, String title, String duration, String description) {
        this.id_formation = id_formation;
        this.title = title;
        this.duration = duration;
        this.description = description;
    }

    public String getId_formation() {
        return id_formation;
    }

    public void setId_formation(String id_formation) {
        this.id_formation = id_formation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
