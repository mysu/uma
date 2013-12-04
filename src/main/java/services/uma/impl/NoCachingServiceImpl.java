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
