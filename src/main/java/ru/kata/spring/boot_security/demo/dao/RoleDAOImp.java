package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleDAOImp implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> query = entityManager.createQuery("FROM Role", Role.class);
        return query.getResultList();

    }

    @Override
    public Role findRoleById(long id) {
        Role role = entityManager.find(Role.class, id);
        return role;
    }

/*    @Override
    public Optional<Role> getRoleByName(String name) {
        return entityManager.createQuery("SELECT role FROM Role role WHERE role.role = :name", Role.class)
                .setParameter("name", "ROLE_" + name)
                .getResultStream()
                .findAny();
    }*/

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("SELECT role FROM Role role WHERE role.role = :name", Role.class)
                .setParameter("name", "ROLE_" + name).getSingleResult();
    }


}
