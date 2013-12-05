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

package dao.uma.user.impl;

import javax.persistence.Query;

import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import models.uma.User;
import dao.AbstractDao;
import dao.uma.user.UserDao;

@Singleton
public class UserDaoImpl extends AbstractDao<User> implements UserDao{
    
    private static final String getByUsername= "select u from User u where u.username = :userName";

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

    @Override
    @Transactional
    public User getByUsername(String username) {
        Query query = super.createQuery(getByUsername);
        query.setParameter("userName", username);
        return (User) getSingleResult(query);
    }
	
	

}
