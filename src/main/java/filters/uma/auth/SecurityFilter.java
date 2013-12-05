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

package filters.uma.auth;

import ninja.Context;
import ninja.Filter;
import ninja.FilterChain;
import ninja.Result;
import ninja.Results;
import ninja.Router;
import ninja.session.SessionCookie;

import com.google.inject.Inject;

import controllers.uma.StartController;

public class SecurityFilter implements Filter {

    @Inject
    private Router router;

    @Override
    public Result filter(FilterChain filterChain, Context context) {
        SessionCookie sessionCookie = context.getSessionCookie();
        if (sessionCookie != null) {
            if (sessionCookie.get("userId") != null) {
                return filterChain.next(context);
            }
        }

        // user is not logged
        return Results.redirect(router.getReverseRoute(StartController.class,
                StartController.Method.index.toString()));
    }

}