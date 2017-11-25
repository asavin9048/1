package service;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.devcolibri.secure.entity.User;
import com.devcolibri.secure.service.UserService;
import com.devcolibri.secure.service.UserServiceImpl;
import org.hibernate.exception.SQLGrammarException;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserServiceImplTest {
    static UserService userService;
    @BeforeClass
    public static void init(){
        userService = new UserServiceImpl();
    }

    @Test
    public void testCorrectValue(){
        User testUser = new User(1,"login","password1");
        assertEquals(userService.getUser("login").equals(testUser), true);
    }
    @Test(expected = SQLGrammarException.class)
    public void testNonexistentValue(){
        User testUser = new User(1,"login","password1");
        assertEquals(userService.getUser("nonexistentlogin").equals(testUser), true);
    }

}
