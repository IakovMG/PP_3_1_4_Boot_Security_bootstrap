package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.entities.User;


import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserDAO userDAO;

    public UserDetailsServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = Optional.ofNullable(userDAO.getUserByUsername(username));
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }

        return user.get();

    }
}