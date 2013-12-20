package controllers.uma;

import ninja.Result;
import ninja.Results;
import ninja.Router;

public class ControllerHelper {
    public static Result redirectToHome(Router router) {
        return Results.redirect(router.getReverseRoute(StartController.class,
                StartController.Method.index.toString()));
    }
}
