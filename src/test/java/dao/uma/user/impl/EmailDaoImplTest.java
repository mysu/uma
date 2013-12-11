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

import static org.junit.Assert.*;
import models.uma.Email;
import models.uma.User;
import ninja.NinjaDaoTestBase;

import org.junit.Before;
import org.junit.Test;

import dao.uma.user.UserDao;

public class EmailDaoImplTest extends NinjaDaoTestBase{

    private EmailDaoImpl emailDao;
    private UserDao userDao;
    
    @Before
    public void setup(){
        emailDao = getInstance(EmailDaoImpl.class);
        userDao = getInstance(UserDaoImpl.class);
    }

    @Test
    public void shouldGetEmailByEmail() {
        User user = new User().setUsername("aName").setPassword("123")
                .setFirstname("first").setLastname("last");
                
        user = userDao.save(user);
        
        Email email = new Email().setEmail("abc@asdf.co").setUser(user);
                
        email = emailDao.save(email);
        assertNotNull(email);
        
        email = emailDao.getByEmail("abc@asdf.co");
        assertNotNull(email);
        assertNotNull(email.getId());
    }

    @Test
    public void shouldReturnNullWhenEmailnameNotExists() {
        assertNull(emailDao.getByEmail("asdf@adsf.co"));
    }


}
