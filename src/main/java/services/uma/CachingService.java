package services.uma;

public interface CachingService {
    <T extends Cacheable<K>,K> T get(Class<T> clazz, K key);
    <T> boolean put(T object, boolean async);
    <T,K> boolean reset(Class<T> clazz, boolean async, @SuppressWarnings("unchecked") K... key);
}
