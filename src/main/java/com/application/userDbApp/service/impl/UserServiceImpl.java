package com.application.userDbApp.service.impl;

import com.application.userDbApp.documents.User;
import com.application.userDbApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by james on 02/06/2016.
 */
@Component("userService")
public class UserServiceImpl implements UserService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User save(User u) {
        mongoTemplate.save(u);
        return u;
    }

    @Override
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public User findUser(String id) {
        return this.findAll()
                .stream().filter(
                        u -> u.id.equals(id))
                .findAny().get();
    }

    @Override
    public User findUser(String username, String password) {
        return this.findAll()
                        .stream().filter(
                        u -> u.username.equals(username)
                                && u.password.equals(password))
                        .findAny().get();
    }

}
