package services.uma;

import java.io.Serializable;

public interface Cacheable<K> extends Serializable{
    K getKey();
    String getClassName();
}
