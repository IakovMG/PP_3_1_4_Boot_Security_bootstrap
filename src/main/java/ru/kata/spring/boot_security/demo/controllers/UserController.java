/*
package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserDAOServiceImp;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDAOServiceImp userDAOServiceImp;
    private final RoleService roleService;

    public UserController(UserDAOServiceImp userDAOServiceImp, RoleService roleService, UserDAOServiceImp userDAOServiceImp1, RoleService roleService1) {

        this.userDAOServiceImp = userDAOServiceImp1;
        this.roleService = roleService1;
    }

    @GetMapping
    public String userPage(Model model, Authentication authentication) {
        //User user = (User) authentication.getPrincipal();
        model.addAttribute("user", authentication.getPrincipal());
        return "pUser";
    }
}*/

package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String userPage(Model model, Authentication authentication) {
        model.addAttribute("user", authentication.getPrincipal());
        return "pUser";
    }
}
