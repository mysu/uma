package repository;

import services.uma.Cacheable;
import services.uma.CachingService;

import com.google.inject.Inject;

//TODO test when no cacheable
public abstract class AbstractRepository<T, K> {

    @Inject
    private CachingService cachingService;

    private final boolean isTypeCacheable;
    {
        if (getType().equals(Cacheable.class)) {
            isTypeCacheable = true;
        } else {
            boolean tempCache = false;
            @SuppressWarnings("rawtypes")
            Class[] interfaces = getType().getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                if (interfaces[i] == Cacheable.class) {
                    tempCache = true;
                    break;
                }
            }
            isTypeCacheable = tempCache;
        }
    }

    public T getById(K id) {
        // 1. try to get from cache
        T object = null;
        if (isTypeCacheable) {
            @SuppressWarnings("unchecked")
            Class<Cacheable<K>> cacheable = (Class<Cacheable<K>>) getType();
            // object2 was created to add SuppressWarnings
            @SuppressWarnings("unchecked")
            T object2 = (T) (cachingService.get(cacheable, id));
            object = object2;
        }
        if (object == null) {
            // 2. try to get from database
            object = getFromDB(id);

            if (object != null) {
                // 3. cache the result
                updateInCache(object);
            }
        }

        return object;
    }

    //TODO test it
    public T save(T object) {
        // 1. Save in database
        T t = persistInDB(object);
        updateInCache(t);
        return t;
    }

    private void updateInCache(T t) {
        if (t instanceof Cacheable) {
            @SuppressWarnings("unchecked")
            Cacheable<K> cacheable = (Cacheable<K>) t;
            cachingService.put(cacheable, false);
        }
    }

    protected abstract T persistInDB(T object);

    protected abstract T getFromDB(K id);

    protected abstract Class<T> getType();

}