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

import static etc.uma.utils.StringUtils.toSha512;

import java.util.Collection;

import models.uma.Email;
import models.uma.User;
import repository.uma.EmailRepository;
import repository.uma.UserRepository;
import services.uma.UserService;

import com.google.inject.Inject;

import etc.uma.dto.RegisterUserDto;
import etc.uma.utils.StringUtils;

public class UserServiceImpl implements UserService {
    private final static int LIMIT_DEFAULT = 10;
    @Inject
    private UserRepository userRepository;
    @Inject
    private EmailRepository emailRepository;

    @Override
    public User getUserById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public Collection<User> getUserList(int offset, Integer limit) {
        return userRepository.getList(offset,
                (limit != null && limit > 0) ? limit : LIMIT_DEFAULT);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User authenticate(String nameOrEmail, String passGiven) {
        User user = getUserByUsernameOrEmail(nameOrEmail);
        if (user != null) {
            if (passGiven != null
                    && toSha512(passGiven).equals(user.getPassword()))
                return user;
        }
        return null;
    }

    @Override
    public User getUserByUsernameOrEmail(String usernameOrEmail) {
        if (StringUtils.isValidEmail(usernameOrEmail)) {
            return userRepository.getByEmail(usernameOrEmail);
        }

        return userRepository.getByUsername(usernameOrEmail);
    }

    @Override
    public User registerUser(RegisterUserDto userDto) {
        if (getUserByUsernameOrEmail(userDto.username) == null
                && getUserByUsernameOrEmail(userDto.email) == null) {
            User user = new User().setUsername(userDto.username).setPassword(StringUtils.toSha512(userDto.password));
            
            user = userRepository.save(user);
            Email email = new Email().setEmail(userDto.email).setUser(user);
            email = emailRepository.save(email);
            
            user.addEmail(email);
            
            return user;
        }
        return null;

    }

}

