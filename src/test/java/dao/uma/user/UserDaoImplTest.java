package dao.uma.user;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import models.uma.User;
import ninja.NinjaDaoTestBase;

import org.junit.Before;
import org.junit.Test;

public class UserDaoImplTest extends NinjaDaoTestBase {
    
    private UserDaoImpl userDao;
    
    @Before
    public void setup(){
        userDao = getInstance(UserDaoImpl.class);
        
    }

    @Test
    public void shouldGetUserByUserName() {
        User user = new User().setUsername("aName").setPassword("123")
                .setFirstname("first").setLastname("last");
                
        user = userDao.save(user);
        assertNotNull(user);
        
        user = userDao.getByUsername("aName");
        assertNotNull(user);
        assertNotNull(user.getId());
    }

    @Test
    public void shouldReturnNullWhenUsernameNotExists() {
        assertNull(userDao.getByUsername("aName"));
    }
}
