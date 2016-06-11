package com.application.userDbApp.controllersTest;

import com.application.UserDbApplication;
import com.application.userDbApp.controllers.UserController;
import com.application.userDbApp.documents.User;
import com.application.userDbApp.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;

/**
 * Created by james on 05/06/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(UserDbApplication.class)
public class UserControllerTest {
    private UserController userController;

    @Mock
    private UserService userService;
    @Autowired
    private MongoTemplate mongoTemplate;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mongoTemplate.dropCollection(User.class);
        mongoTemplate.createCollection(User.class);
        userController = new UserController(userService);
    }

    @Test
    public void canRegisterANewUser() {
        User testUser =
                new User("testFirst", "testLast", "testLocation", "testName", "testUser", "testPass", "test@test.com");
        given(userService.save(testUser)).willReturn(testUser);

        User returnedUser = userController
                .registerNewUser(testUser.firstName,
                        testUser.lastName,
                        testUser.clubLocation,
                        testUser.instructorName,
                        testUser.username,
                        testUser.password,
                        testUser.email);
        assertTrue("User username is not as expected", returnedUser.username.equals("testUser"));
    }

    @Test
    public void returnsAnEmptyUserFromIncompleteDetails() {
        User testUser =
                new User("", "testLast", "testLocation", "testName", "testUser", "testPass", "test@test.com");

        User returnedUser = userController
                .registerNewUser(testUser.firstName,
                        testUser.lastName,
                        testUser.clubLocation,
                        testUser.instructorName,
                        testUser.username,
                        testUser.password,
                        testUser.email);
        assertTrue("User id should have been set to 0", returnedUser.id.equals("0"));
    }
}
