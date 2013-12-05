package filters.uma.auth;

import ninja.Context;
import ninja.Filter;
import ninja.FilterChain;
import ninja.Result;
import ninja.Results;
import ninja.Router;
import ninja.session.SessionCookie;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import controllers.uma.StartController;

/**
 * This Filter avoids to get into pages that are for unauthenticated users like
 * '/', 'login', 'register' If user is authenticated, he/she is redirected to
 * /home
 * 
 * @author emiguelt 05/12/2013
 * 
 */
@Singleton
public class ToHomeFilter implements Filter {

    @Inject
    private Router router;

    @Override
    public Result filter(FilterChain filterChain, Context context) {
        SessionCookie sessionCookie = context.getSessionCookie();
        if (sessionCookie != null && sessionCookie.get("userId") != null) {
            // user must be authenticated, will be redirected to /home
            return Results.redirect(router.getReverseRoute(
                    StartController.class,
                    StartController.Method.index.toString()));
        }
        
        return filterChain.next(context);
    }

}
