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

package conf;

import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import ninja.utils.NinjaProperties;

import com.google.inject.Inject;

import controllers.uma.StartController;
import controllers.uma.api.user.ApiUserController;
import controllers.uma.auth.LoginController;
import controllers.uma.user.HomeController;

public class Routes implements ApplicationRoutes {

    @Inject
    NinjaProperties ninjaProperties;

    /**
     * Using a (almost) nice DSL we can configure the router.
     * 
     * The second argument NinjaModuleDemoRouter contains all routes of a
     * submodule. By simply injecting it we activate the routes.
     * 
     * @param router
     *            The default router of this application
     */
    @Override
    public void init(Router router) {

        // /////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        // /////////////////////////////////////////////////////////////////////
        router.GET().route("/assets/.*").with(AssetsController.class, "serve");

        // /////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        // /////////////////////////////////////////////////////////////////////
        // router.GET().route("/.*").with(ApplicationController.class, "index");

        router.GET()
                .route("/")
                .with(StartController.class,
                        StartController.Method.index.toString());

        router.GET()
                .route("/home/")
                .with(HomeController.class,
                        HomeController.Method.index.toString());

        router.GET()
                .route("/login")
                .with(LoginController.class,
                        LoginController.Method.login.toString());
        router.POST()
                .route("/login")
                .with(LoginController.class,
                        LoginController.Method.doLogin.toString());
        router.GET()
                .route("/logout")
                .with(LoginController.class,
                        LoginController.Method.logout.toString());

        router.GET()
                .route("/api/users")
                .with(ApiUserController.class,
                        ApiUserController.Method.listUsers.toString());
    }

}
