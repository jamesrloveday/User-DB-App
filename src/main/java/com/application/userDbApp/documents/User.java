package com.application.userDbApp.documents;

import org.springframework.data.annotation.Id;

/**
 * Created by james on 02/06/2016.
 */
public class User {

    @Id
    public String id;
    public String firstName;
    public String lastName;
    public String clubLocation;
    public String instructorName;
    public String username;
    public String password;
    public String email;

    public User(){}

    public User(String id) {
        this.id = id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String firstName,
                String lastName,
                String clubLocation,
                String instructorName,
                String username,
                String password,
                String email) {
        this(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.clubLocation = clubLocation;
        this.instructorName = instructorName;
        this.email = email;
    }
}
