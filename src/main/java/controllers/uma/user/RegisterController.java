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

package controllers.uma.user;

import models.uma.User;
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.i18n.Lang;
import ninja.i18n.Messages;
import services.uma.UserService;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import etc.uma.dto.RegisterUserDto;
import etc.uma.utils.LoginHelper;
import filters.uma.auth.ToHomeFilter;

@Singleton
public class RegisterController {
    public static enum Method {
        index, postRegister
    }

    @Inject
    UserService userService;

    @Inject
    Messages i18n;
    @Inject
    Lang lang;

    @FilterWith(ToHomeFilter.class)
    public Result index() {
        // show form
        return Results.html();
    }

    @FilterWith(ToHomeFilter.class)
    public Result postRegister(RegisterUserDto userDto, Context context) {
        User newUser = userService.registerUser(userDto);
        if (newUser != null) {
            // user registered
            LoginHelper.addUserToSession(newUser, context);
            return Results.json().render("success", true);
        }

        return Results
                .json()
                .render("success", false)
                .render("error",
                        i18n.get("registerUser.generalError", Optional.of("en")));
    }
}
