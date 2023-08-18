package ru.kata.spring.boot_security.demo.services;


import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {
    void updateUser(long id, User user);

    void deleteUser(long id);

    void saveUser(User user);

    List<User> getAllUsers();

    User getUserById(long id);

}