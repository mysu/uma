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

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import models.uma.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import repository.uma.UserRepository;
import services.uma.impl.UserServiceImpl;

@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class UserServiceImplTest {
    
    private static final String ADMIN_PASSWORD = "c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec";
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetUserById() {
        userService.getUserById(1);
        verify(userRepository).getById(1L);
    }
    
    @Test
    public void testGetUserByUsername() {
        userService.getUserByUsernameOrEmail("anUser");
        verify(userRepository).getByUsername("anUser");
    }

    @Test
    public void testGetUserList() {
       userService.getUserList(0, null);
       verify(userRepository).getList(0, 10);
    }

    @Test
    public void testSaveUser() {
        userService.saveUser(new User());
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testValidAuthentication() {
        when(userRepository.getByUsername("anUser")).then(new Answer<User>() {

            @Override
            public User answer(InvocationOnMock invocation) throws Throwable {
                User user = new User();
                user.setId(1L).setUsername("anUser").setPassword(ADMIN_PASSWORD);
                return user;
            }
        });
        
        assertNotNull(userService.authenticate("anUser", "admin"));
        verify(userRepository).getByUsername("anUser");
    }
    

    @Test
    public void testInvalidAuthentication() {
        when(userRepository.getByUsername("anUser")).then(new Answer<User>() {

            @Override
            public User answer(InvocationOnMock invocation) throws Throwable {
                User user = new User();
                user.setId(1L).setUsername("anUser").setPassword("123");
                return user;
            }
        });
        
        assertNull(userService.authenticate("anUser", "12"));
        assertNull(userService.authenticate("oterUser", "12"));
        verify(userRepository).getByUsername("anUser");
    }    

}
