package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class UserDAOImp implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void updateUser(long id, User user) {
        user.setId(id);
        entityManager.merge(user);
    }


    @Override
    public void deleteUser(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);

    }

    @Override
    public User getUserByUsername(String username) {
        return entityManager.createQuery("SELECT user from User user where user.username = :username " +
                        "or user.email = :username ", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }


}

