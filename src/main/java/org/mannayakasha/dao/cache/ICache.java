package org.mannayakasha.dao.cache;

import org.mannayakasha.model.Entity;

/**
 * Interface that represents base operations with cache.
 *
 * @author Pavel
 * @version 1.0 03.08.2017.
 */
public interface ICache<T extends Entity> {
    T get(Integer id);

    void set(T entity);

    void delete(Integer id);

    void clear();
}
