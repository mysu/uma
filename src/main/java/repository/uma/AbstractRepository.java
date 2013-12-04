/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package repository.uma;

import services.uma.Cacheable;
import services.uma.CachingService;

import com.google.inject.Inject;

//TODO document it
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

    public T save(T object) {
        // 1. Save in database
        T t = persistInDB(object);
        updateInCache(t);
        return t;
    }

    private void updateInCache(T t) {
        if (isTypeCacheable) {
            @SuppressWarnings("unchecked")
            Cacheable<K> cacheable = (Cacheable<K>) t;
            cachingService.put(cacheable, false);
        }
    }

    protected abstract T persistInDB(T object);

    protected abstract T getFromDB(K id);

    protected abstract Class<T> getType();

}
