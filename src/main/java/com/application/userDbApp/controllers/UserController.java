package com.application.userDbApp.controllers;

import com.application.userDbApp.documents.User;
import com.application.userDbApp.service.UserService;
import com.application.userDbApp.validator.FieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by james on 02/06/2016.
 */
@RestController
@RequestMapping("/user")
public class UserController implements FieldValidator {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User registerNewUser(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("clubLocation") String clubLocation,
                                @RequestParam("instructorName") String instructorName,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("email") String email
                                ) {
        if(isNotNull(username) &&
                isNotNull(password) &&
                isNotNull(email) &&
                isNotNull(instructorName) &&
                isNotNull(clubLocation) &&
                isNotNull(firstName) &&
                isNotNull(lastName)) {
            final User user =
                    new User(firstName, lastName, clubLocation, instructorName, username, password, email);
            userService.save(user);
           return user;
        } else return new User("0");
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    public boolean authenticateUser(@RequestParam("username") String username,
                                    @RequestParam("password") String password) {
       return false;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public User updateUser(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email,
                           @RequestParam("instructorName") String instructorName,
                           @RequestParam("clubLocation") String clubLocation,
                           @RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName) {
        throw new UnsupportedOperationException("Not yet ready");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email,
                           @RequestParam("instructorName") String instructorName,
                           @RequestParam("clubLocation") String clubLocation,
                           @RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName) {
        throw new UnsupportedOperationException("Not yet ready");
    }
}
