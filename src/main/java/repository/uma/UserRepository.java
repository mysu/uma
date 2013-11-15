package repository.uma;

import java.util.Collection;

import models.uma.User;

public interface UserRepository {

    Collection<User> getList(int offset, Integer limit);

    User getById(Long id);

    User save(User user);

    User getByUsername(String username);

}
