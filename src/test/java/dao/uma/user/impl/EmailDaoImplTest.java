package dao.uma.user.impl;

import static org.junit.Assert.*;
import models.uma.Email;
import models.uma.User;
import ninja.NinjaDaoTestBase;

import org.junit.Before;
import org.junit.Test;

import dao.uma.user.UserDao;

public class EmailDaoImplTest extends NinjaDaoTestBase{

    private EmailDaoImpl emailDao;
    private UserDao userDao;
    
    @Before
    public void setup(){
        emailDao = getInstance(EmailDaoImpl.class);
        userDao = getInstance(UserDaoImpl.class);
    }

    @Test
    public void shouldGetEmailByEmail() {
        User user = new User().setUsername("aName").setPassword("123")
                .setFirstname("first").setLastname("last");
                
        user = userDao.save(user);
        
        Email email = new Email().setEmail("abc@asdf.co").setUser(user);
                
        email = emailDao.save(email);
        assertNotNull(email);
        
        email = emailDao.getByEmail("abc@asdf.co");
        assertNotNull(email);
        assertNotNull(email.getId());
    }

    @Test
    public void shouldReturnNullWhenEmailnameNotExists() {
        assertNull(emailDao.getByEmail("asdf@adsf.co"));
    }


}
