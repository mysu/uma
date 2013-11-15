package services.uma.impl;

import java.util.Collection;

import models.uma.User;
import repository.uma.UserRepository;
import services.uma.UserService;

import com.google.inject.Inject;

public class UserServiceImpl implements UserService {
    private final static int LIMIT_DEFAULT = 10;
    @Inject
    private UserRepository userRepository;

    @Override
    public User getUserById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public Collection<User> getUserList(int offset, Integer limit) {
        return userRepository.getList(offset, (limit != null && limit > 0) ? limit
                : LIMIT_DEFAULT);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
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
    //TODO finish this feature, add email support
    public User getUserByUsernameOrEmail(String userName) {
        return userRepository.getByUsername(userName);
    }

}
