package com.application.userDbApp.service;

import com.application.userDbApp.documents.User;
import com.application.userDbApp.exceptions.UserNotFoundException;

import java.util.List;

/**
 * Created by james on 02/06/2016.
 */
public interface UserService {

    User save(User u);
    List<User> findAll();
    User findUser(String id);
    User findUser(String username, String password);
}
