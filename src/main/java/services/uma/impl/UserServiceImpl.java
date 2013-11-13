package services.uma.impl;

import java.util.Collection;

import com.google.inject.Inject;

import dao.uma.user.UserDao;
import models.uma.User;
import services.uma.UserService;

public class UserServiceImpl implements UserService {
    private final static int LIMIT_DEFAULT = 10;
    @Inject
    private UserDao userDao;

    @Override
    public User getUserById(long id) {
        return userDao.get(id);
    }

    @Override
    public Collection<User> getUserList(int offset, Integer limit) {
        return userDao.getList(offset, (limit != null && limit > 0) ? limit
                : LIMIT_DEFAULT);
    }

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User authenticate(String nameOrEmail, String passGiven) {
        User user = getUserByUsernameOrEmail(nameOrEmail);
        if (user != null) {
            if (passGiven != null && passGiven.equals(user.getPassword()))
                return user;
        }
        return null;
    }

    @Override
    public User getUserByUsernameOrEmail(String userName) {
        return userDao.getByUsername(userName);
    }

}
