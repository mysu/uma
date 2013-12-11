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

package repository.uma.impl;

import java.util.Collection;

import models.uma.Email;
import models.uma.User;
import repository.uma.AbstractRepository;
import repository.uma.UserRepository;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import dao.uma.user.EmailDao;
import dao.uma.user.UserDao;

@Singleton
public class UserRepositoryImpl extends AbstractRepository<User, Long>implements UserRepository {
    
    @Inject
    private UserDao userDao;
    @Inject
    private EmailDao emailDao;

    @Override
    public Collection<User> getList(int offset, Integer limit) {
        // TODO get all users from cache
        return userDao.getList(offset, limit);
    }

    @Override
    public User getByUsername(String username) {
        // TODO get id from DB and get User from cache
        return userDao.getByUsername(username);
    }

    @Override
    protected User persistInDB(User user) {
        return userDao.save(user);
    }

    @Override
    protected User getFromDB(Long id) {
        return userDao.get(id);
    }

    @Override
    protected Class<User> getType() {
        return User.class;
    }

    @Override
    public User getByEmail(String usernameOrEmail) {
        Email email = emailDao.getByEmail(usernameOrEmail);
        if(email!=null)
            return email.getUser();
            
        return null;
    }


}
