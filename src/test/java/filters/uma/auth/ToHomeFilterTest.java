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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import ninja.Context;
import ninja.FilterChain;
import ninja.Result;
import ninja.Router;
import ninja.session.SessionCookie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import controllers.uma.StartController;

@RunWith(MockitoJUnitRunner.class)
public class ToHomeFilterTest {
    
    @InjectMocks
    private ToHomeFilter homeFilter;
    
    @Mock
    private FilterChain filterChain;
    @Mock
    private SessionCookie sessionCookie;
    @Mock
    private Context context;
    @Mock
    private Router router;

    @Test
    public void shouldRedirectToHome() {
        when(context.getSessionCookie()).thenReturn(sessionCookie);
        when(sessionCookie.get("userId")).thenReturn("1");
        when(router.getReverseRoute(StartController.class, StartController.Method.index.toString())).thenReturn("/");

        homeFilter.filter(filterChain, context);

        verifyZeroInteractions(filterChain);
        verify(router).getReverseRoute(StartController.class, StartController.Method.index.toString());
        verify(sessionCookie).get("userId");
    }

}
