package services.uma.impl;

import services.uma.Cacheable;
import services.uma.CachingService;

import com.google.inject.Singleton;

/**
 * Dumb class that implements Caching Service, but it does not cache anything...
 * Use for development or when not want to use caching in production
 * @author emiguelt
 * @param <K>
 *
 */
@Singleton
public class NoCachingServiceImpl<K> implements CachingService{

    @Override
    public <T extends Cacheable<K>, K> T get(Class<T> clazz, K key) {
        return null;
    }

    @Override
    public <T> boolean put(T object, boolean async) {
        return true;
    }

    @Override
    public <T, K> boolean reset(Class<T> clazz, boolean async, K... key) {
        return true;
    }



}
