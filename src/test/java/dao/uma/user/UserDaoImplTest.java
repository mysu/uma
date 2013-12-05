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

package dao.uma.user;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import models.uma.User;
import ninja.NinjaDaoTestBase;

import org.junit.Before;
import org.junit.Test;

import dao.uma.user.impl.UserDaoImpl;

public class UserDaoImplTest extends NinjaDaoTestBase {
    
    private UserDaoImpl userDao;
    
    @Before
    public void setup(){
        userDao = getInstance(UserDaoImpl.class);
        
    }

    @Test
    public void shouldGetUserByUserName() {
        User user = new User().setUsername("aName").setPassword("123")
                .setFirstname("first").setLastname("last");
                
        user = userDao.save(user);
        assertNotNull(user);
        
        user = userDao.getByUsername("aName");
        assertNotNull(user);
        assertNotNull(user.getId());
    }

    @Test
    public void shouldReturnNullWhenUsernameNotExists() {
        assertNull(userDao.getByUsername("aName"));
    }
}
