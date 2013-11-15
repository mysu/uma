package controllers.uma.api.user;

import java.util.Collection;

import models.uma.User;
import ninja.Result;
import ninja.Results;
import services.uma.UserService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ApiUserController {
    @Inject
    private UserService userService;
    
    public Result listUsers(){
        Collection<User> users = userService.getUserList(0, null);
        return Results.json().render(users);
    }
}
