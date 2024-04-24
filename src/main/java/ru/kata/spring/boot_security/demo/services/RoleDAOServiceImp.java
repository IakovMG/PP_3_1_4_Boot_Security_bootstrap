package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.entities.Role;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class RoleDAOServiceImp implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleDAOServiceImp(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Override
    public Role findRoleById(int id) {
        return roleDAO.findRoleById(id);
    }

/*    @Override
    public Optional<Role> getRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }*/

    @Override
    public Role getRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }
}

