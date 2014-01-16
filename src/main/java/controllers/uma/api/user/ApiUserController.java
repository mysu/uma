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

package controllers.uma.api.user;

import java.util.Collection;

import models.uma.User;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import services.uma.UserService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import etc.uma.dto.BasicInfoDto;

@Singleton
public class ApiUserController {
    public static enum Method {
        listUsers, validUser, validEmail, updateBasicInfo
    }

    @Inject
    private UserService userService;

    public Result listUsers() {
        Collection<User> users = userService.getUserList(0, null);
        return Results.json().render(users);
    }

    public Result validUser(@Param("username") String username) {
        User user = userService.getUserByUsernameOrEmail(username);
        return Results.json().render(user == null);
    }

    public Result validEmail(@Param("email") String email) {
        User user = userService.getUserByUsernameOrEmail(email);
        return Results.json().render(user == null);
    }

    public Result updateBasicInfo(BasicInfoDto basic, Context ctx) {
        return Results.json().render("success",
                userService.updateBasicInfo(basic));
    }
}
