package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;
import java.util.Optional;


public interface RoleDAO {


    List<Role> getAllRoles();

    Role findRoleById(long id);

    /*    Optional<Role> getRoleByName(String name);*/
    Role getRoleByName(String name);


}
