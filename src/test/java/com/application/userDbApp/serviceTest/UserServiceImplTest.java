package com.application.userDbApp.serviceTest;

import com.application.UserDbApplication;
import com.application.userDbApp.documents.User;
import com.application.userDbApp.service.UserService;
import com.application.userDbApp.service.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by james on 04/06/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(UserDbApplication.class)
public class UserServiceImplTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    private UserService userService;
    private User user;

    @Before
    public void setUp() {
        mongoTemplate.dropCollection(User.class);
        mongoTemplate.createCollection(User.class);
        userService = new UserServiceImpl(mongoTemplate);
        user = new User("testFirst",
                "testLast",
                "testLocation",
                "test instructor",
                "testUsername",
                "testPassword",
                "test@test.com");
    }

    @Test
    public void saveUserWithGivenData() {
        User saved = userService.save(user);
        assertTrue("Saved user is not as expected", !saved.id.isEmpty());
    }

    @Test
    public void findAKnownUser() {
        userService.save(user);
        User testUser = userService.findUser("testUsername", "testPassword");
        assertTrue("User id not as expected", !testUser.id.isEmpty());
    }

    @Test
    public void findKnownUserWithId1() {
        user.id = "1";
        userService.save(user);
        User testUser = userService.findUser("1");
        assertTrue("User is not as expected", user.username.equals("testUsername"));
    }
}
