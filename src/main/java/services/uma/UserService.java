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

import java.util.Collection;

import etc.uma.dto.BasicInfoDto;
import etc.uma.dto.RegisterUserDto;
import models.uma.User;

public interface UserService {
    User getUserById(long id);
    Collection<User> getUserList(int offset, Integer limit);
    User saveUser(User user);
    User authenticate(String nameOrEmail, String passGiven);
    User getUserByUsernameOrEmail(String string);
    User registerUser(RegisterUserDto userDto);
    boolean updateBasicInfo(BasicInfoDto bInfo);
}
