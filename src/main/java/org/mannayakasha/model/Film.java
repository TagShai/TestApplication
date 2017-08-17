package org.mannayakasha.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean object, that represents entity film
 *
 * @author Pavel
 * @version 1.0 13.08.2017.
 */
public class Film extends Entity {
    private String name;
    private String description;
    private Set<Genre> genres = new HashSet<>();
    private String country;
    private String quality;
    private String image;

    public Film() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // TODO: 13.08.2017 equals(), hashcode()


    @Override
    public String toString() {
        return "Film{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", genres=" + genres +
                ", country='" + country + '\'' +
                ", quality='" + quality + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
