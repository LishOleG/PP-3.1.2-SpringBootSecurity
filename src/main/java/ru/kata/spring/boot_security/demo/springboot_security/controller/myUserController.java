package ru.kata.spring.boot_security.demo.springboot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.springboot_security.service.UserService;

import java.security.Principal;

@Controller
public class myUserController {

    @Autowired
    private final UserService userService;

    public myUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUserProfile(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByNameWithRoles(principal.getName()));
        return "user";
    }


}
