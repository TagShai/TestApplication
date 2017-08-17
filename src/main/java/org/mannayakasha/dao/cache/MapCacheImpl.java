package org.mannayakasha.dao.cache;

import org.mannayakasha.model.Entity;

import java.util.Map;

/**
 * Implementation of {@link ICache} service.
 *
 * @author Pavel
 * @version 1.0 04.08.2017.
 */
public class MapCacheImpl implements ICache {
    private Map<Integer, Entity> cache;

    public void setCache(Map<Integer, Entity> cache) {
        this.cache = cache;
    }

    @Override
    public Entity get(Integer id) {
        return cache.get(id);
    }

    @Override
    public void set(Entity entity) {
        cache.put(entity.getId(), entity);
    }

    @Override
    public void delete(Integer id) {
        cache.remove(id);
    }

    @Override
    public void clear() {
        cache.clear();
    }
}
