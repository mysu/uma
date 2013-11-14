package repository.impl;

import java.util.Collection;

import models.uma.User;
import repository.AbstractRepository;
import repository.UserRepository;

import com.google.inject.Inject;

import dao.uma.user.UserDao;

public class UserRepositoryImpl extends AbstractRepository<User, Long>implements UserRepository {
    
    @Inject
    private UserDao userDao;

    @Override
    public Collection<User> getList(int offset, Integer limit) {
        // TODO get all users from cache
        return userDao.getList(offset, limit);
    }

    @Override
    public User getByUsername(String username) {
        // TODO get id from DB and get User from cache
        return userDao.getByUsername(username);
    }

    @Override
    protected User persistInDB(User user) {
        return userDao.save(user);
    }

    @Override
    protected User getFromDB(Long id) {
        return userDao.get(id);
    }

    @Override
    protected Class<User> getType() {
        return User.class;
    }


}
