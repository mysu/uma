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

package etc.uma.extractors;

import models.uma.User;
import ninja.Context;
import ninja.params.ArgumentExtractor;
import ninja.session.SessionCookie;
import services.uma.UserService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LoggedUserExtractor implements ArgumentExtractor<User> {

    private UserService userService;
    
    @Inject public LoggedUserExtractor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User extract(Context context) {
        SessionCookie sessionCookie = context.getSessionCookie();
        if (sessionCookie != null) {
            String userId = sessionCookie.get("userId");
            if (userId != null) {
                return userService.getUserById(Long.valueOf(userId));
            }
        }

        return null;
    }

    @Override
    public Class<User> getExtractedType() {
        return User.class;
    }

    @Override
    public String getFieldName() {
        return null;
    }

}
