package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserDAOServiceImp;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserDAOServiceImp userDAOServiceImp;
    private final RoleService roleService;


    public AdminController(UserDAOServiceImp userDAOServiceImp, RoleService roleService) {
        this.userDAOServiceImp = userDAOServiceImp;
        this.roleService = roleService;
    }

    @GetMapping()
    public String adminPage(Model model, Authentication authentication) {
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("allUsers", userDAOServiceImp.getAllUsers());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "pAdmin";
    }

    @PostMapping()
    public String creatUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "newUser";
        }
        Set<Role> roles = new HashSet<>();
        Set<Role> selectRoles = user.getRoles();
        if (selectRoles != null && !selectRoles.isEmpty()) {
            for (Role name : selectRoles) {
                roles.add(roleService.getRoleByName(name.toString()));
            }
        }
        user.setRoles(roles);
        userDAOServiceImp.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") @Valid User user) {

        Set<Role> roles = new HashSet<>();
        Set<Role> selectRoles = user.getRoles();
        if (selectRoles != null && !selectRoles.isEmpty()) {
            for (Role name : selectRoles) {
                roles.add(roleService.getRoleByName(name.toString()));
            }
        }
        user.setRoles(roles);
        userDAOServiceImp.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userDAOServiceImp.deleteUser(id);
        return "redirect:/admin";
    }
}