package etc.uma.utils;

import ninja.Context;
import models.uma.User;

public class LoginHelper {

    public static void addUserToSession(User user, Context context) {
     // set the user in the session
        context.getSessionCookie().put("userId", user.getId().toString());
    }

}
