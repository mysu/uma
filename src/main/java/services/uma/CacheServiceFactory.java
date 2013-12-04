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

package services.uma;

import services.uma.impl.NoCachingServiceImpl;
import ninja.utils.NinjaProperties;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import conf.AppProperties;

@Singleton
public class CacheServiceFactory {
    private NinjaProperties ninjaProperties;
    
    @Inject
    public CacheServiceFactory(NinjaProperties ninjaProperties) {
        this.ninjaProperties = ninjaProperties;
    }
    
    public CachingService getCachingService(Injector injector){
        if (ninjaProperties.getBoolean(AppProperties.UMA_CACHE_SERVER_ENABLED.getKey())){
            //TODO make a real implementation
            return injector.getInstance(NoCachingServiceImpl.class);
        }else{
            //no cache
            return injector.getInstance(NoCachingServiceImpl.class);
        }
    }
}
