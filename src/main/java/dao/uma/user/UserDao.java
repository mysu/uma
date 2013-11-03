package dao.uma.user;

import models.uma.User;
import dao.AbstractDao;

public class UserDao extends AbstractDao<User> {

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

}
