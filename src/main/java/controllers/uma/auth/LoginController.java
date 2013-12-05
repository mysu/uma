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
import ninja.Result;
import ninja.Results;
import ninja.Router;
import ninja.params.Param;
import services.uma.UserService;
import uma.annotations.LoggedUser;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import controllers.uma.StartController;
import controllers.uma.user.HomeController;

@Singleton
public class LoginController {
    public static enum Method {
        login, doLogin, logout
    }

    @Inject
    private Router router;

    @Inject
    private UserService userService;

    public Result doLogin(@LoggedUser User user,
                          @Param("username") String username,
                          @Param("password") String password,
                          Context context) {
        if (user != null) {
            // user is authenticated
            return redirectToHome(user);
        }

        user = userService.authenticate(username, password);

        if (user != null) {
            // set the user in the session
            context.getSessionCookie().put("userId", user.getId().toString());
            return redirectToHome(user);
        }

        context.getFlashCookie().put("username", username);
        context.getFlashCookie().error("login.error");
        return Results.redirect(LoginController.Method.login.toString());
    }

    public Result login() {
        return Results.html();
    }

    public Result logout(Context context) {
        context.getSessionCookie().clear();
        return Results.redirect(router.getReverseRoute(StartController.class,
                StartController.Method.index.toString()));
    }

    private Result redirectToHome(User user) {
        return Results.redirect(router.getReverseRoute(HomeController.class,
                HomeController.Method.index.toString()));
    }
}
