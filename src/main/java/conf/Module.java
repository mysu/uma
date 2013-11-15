/**
 * Copyright (C) 2012 the original author or authors.
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

package conf;

import ninja.utils.NinjaModeHelper;
import ninja.utils.NinjaProperties;
import ninja.utils.NinjaPropertiesImpl;
import repository.uma.UserRepository;
import repository.uma.impl.UserRepositoryImpl;
import services.uma.CachingService;
import services.uma.UserService;
import services.uma.impl.NoCachingServiceImpl;
import services.uma.impl.UserServiceImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import dao.uma.user.UserDao;
import dao.uma.user.UserDaoImpl;

@Singleton
public class Module extends AbstractModule {
  
    NinjaProperties ninjaProperties;
    
    public Module() {
        //TODO FIX THAT with something better
        ninjaProperties = new NinjaPropertiesImpl(
                NinjaModeHelper.determineModeFromSystemPropertiesOrDevIfNotSet()); 
    }

    protected void configure() {
        // 1.
        configureCachingService();
        // 2.
        bindDAOs();
        //3.
        bindRepos();
        //4.
        bindServices();
        //5.
        bind(StartupActions.class);

    }
 
    private void bindServices() {
        bind(UserService.class).to(UserServiceImpl.class);
    }

    private void bindRepos() {
        bind(UserRepository.class).to(UserRepositoryImpl.class);
    }

    private void bindDAOs() {
        bind(UserDao.class).to(UserDaoImpl.class);
    }

    private void configureCachingService() {
        if (ninjaProperties.getBoolean(AppProperties.UMA_CACHE_SERVER_ENABLED
                .getKey())) {
            // TODO configure a real server implementation
        } else {
            bind(CachingService.class).to(NoCachingServiceImpl.class);
        }

    }
}
