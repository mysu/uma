package services;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import models.uma.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import services.uma.impl.UserServiceImpl;
import dao.uma.user.UserDao;

@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class UserServiceImplTest {
    
    @Mock
    private UserDao userDao;
    
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetUserById() {
        userService.getUserById(1);
        verify(userDao).get(1);
    }
    
    @Test
    public void testGetUserByUsername() {
        userService.getUserByUsernameOrEmail("anUser");
        verify(userDao).getByUsername("anUser");
    }

    @Test
    public void testGetUserList() {
       userService.getUserList(0, null);
       verify(userDao).getList(0, 10);
    }

    @Test
    public void testSaveUser() {
        userService.saveUser(new User());
        verify(userDao).save(any(User.class));
    }

    @Test
    public void testAuthenticate() {
        when(userDao.getByUsername("anUser")).then(new Answer<User>() {

            @Override
            public User answer(InvocationOnMock invocation) throws Throwable {
                User user = new User();
                user.setId(1L).setUsername("anUser").setPassword("123");
                return user;
            }
        });
        
        assertNotNull(userService.authenticate("anUser", "123"));
        verify(userDao).getByUsername("anUser");
        //TODO finish this test
    }

}
