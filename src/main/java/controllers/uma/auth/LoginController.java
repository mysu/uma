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

package controllers.uma.auth;

import models.uma.User;
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.Router;
import ninja.params.Param;
import services.uma.UserService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import controllers.uma.StartController;
import filters.uma.auth.ToHomeFilter;

@Singleton
public class LoginController {
    public static enum Method {
        login, doLogin, logout
    }

    @Inject
    private Router router;

    @Inject
    private UserService userService;

    @FilterWith(ToHomeFilter.class)
    public Result doLogin(@Param("username") String username,
                          @Param("password") String password,
                          Context context) {

        User user = userService.authenticate(username, password);

        if (user != null) {
            // set the user in the session
            context.getSessionCookie().put("userId", user.getId().toString());
            return redirectToHome();
        }

        // TODO add error messages
        context.getFlashCookie().put("username", username);
        context.getFlashCookie().error("login.error");
        return Results.redirect(LoginController.Method.login.toString());
    }

    @FilterWith(ToHomeFilter.class)
    public Result login() {
        return Results.html();
    }

    public Result logout(Context context) {
        context.getSessionCookie().clear();
        return redirectToHome();
    }

    private Result redirectToHome() {
        return Results.redirect(router.getReverseRoute(StartController.class,
                StartController.Method.index.toString()));
    }
}
