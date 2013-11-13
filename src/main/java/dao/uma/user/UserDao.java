package dao.uma.user;

import java.util.Collection;

import models.uma.User;

public interface UserDao {
    Collection<User> getList(int offset, Integer limit);
    User get(long id);
    User save(User user);
    User getByUsername(String username);
}
