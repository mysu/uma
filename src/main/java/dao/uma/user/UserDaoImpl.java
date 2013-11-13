package dao.uma.user;

import javax.persistence.Query;

import com.google.inject.persist.Transactional;

import models.uma.User;
import dao.AbstractDao;

public class UserDaoImpl extends AbstractDao<User> implements UserDao{
    
    private static final String getByUsername= "select u from User u where u.username = :userName";

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

    @Override
    @Transactional
    public User getByUsername(String username) {
        Query query = getEM().createQuery(getByUsername);
        query.setParameter("userName", username);
        return (User) query.getSingleResult();
    }
	
	

}
