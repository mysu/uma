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

package conf;

import repository.uma.UserRepository;
import repository.uma.impl.UserRepositoryImpl;
import services.uma.CacheServiceFactory;
import services.uma.CachingService;
import services.uma.UserService;
import services.uma.impl.UserServiceImpl;
import co.leugim.jade4ninja.Jade4NinjaModule;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import dao.uma.user.EmailDao;
import dao.uma.user.UserDao;
import dao.uma.user.impl.EmailDaoImpl;
import dao.uma.user.impl.UserDaoImpl;

@Singleton
public class Module extends AbstractModule {
  
    protected void configure() {
        
        install(new Jade4NinjaModule());
        
        // 1.
        bindDAOs();
        // 2.
        bindRepos();
        //3.
        bindServices();
        //4.
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
        bind(EmailDao.class).to(EmailDaoImpl.class);
    }

    @Provides
    @Singleton
    public CachingService configureCachingService(Injector injector, CacheServiceFactory cacheServiceFactory) {
        return cacheServiceFactory.getCachingService(injector);
    }
}
