package ru.kata.spring.boot_security.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.entities.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserDAOServiceImp implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDAOServiceImp(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void updateUser(long id, User user) {
        String password = user.getPassword();
        if (password.isEmpty()) {
            String passwordOne = userDAO.getUserById(id).getPassword();
            user.setPassword(passwordOne);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDAO.updateUser(id, user);
    }

    @Override
    public void deleteUser(long id) {
        userDAO.deleteUser(id);
    }

    @Override
    public void saveUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }
}