package org.mannayakasha.model;

import java.io.Serializable;

/**
 * Base class for all entities
 *
 * @author Pavel
 * @version 1.0 03.08.2017
 */
public abstract class Entity implements Serializable {
    private Integer id;

    public Entity() {}

    public Entity(Integer id) {
        this.id = id;
    }

/*    public int getId() {
        return id;
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
