package services.uma;

import java.util.Collection;

import models.uma.User;

public interface UserService {
    User getUserById(long id);
    Collection<User> getUserList(int offset, Integer limit);
    User saveUser(User user);
    User authenticate(String nameOrEmail, String passGiven);
    User getUserByUsernameOrEmail(String string);
}
