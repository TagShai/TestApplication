package org.mannayakasha.model;

/**
 * Simple JavaBean object, that represents entity genre
 *
 * @author Pavel
 * @version 1.0 13.08.2017.
 */
public class Genre extends Entity {
    private String name;

    public Genre() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // TODO: 13.08.2017 equals(), hashcode()

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                '}';
    }
}
