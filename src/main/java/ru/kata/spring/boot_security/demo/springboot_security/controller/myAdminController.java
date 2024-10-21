package ru.kata.spring.boot_security.demo.springboot_security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.springboot_security.model.Role;
import ru.kata.spring.boot_security.demo.springboot_security.model.User;
import ru.kata.spring.boot_security.demo.springboot_security.service.RoleService;
import ru.kata.spring.boot_security.demo.springboot_security.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class myAdminController {

    private final UserService userService;
    private final RoleService roleService;

//    List<User> getAllUsers();
//    void save(User user);
//    User showUserById(long id);
//    void update(long id, User user);
//    void delete(long id);
//    User getUserByNameWithRoles(String name);
//    String bcryptPass(String pass);
//
    @Autowired
    public myAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String allUserTable(Model model) {
        model.addAttribute("users", userService.getAllUsers()); //<-findAllUsers());
        return "users";
    }

    @GetMapping("/user")
    public String showUser(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.showUserById(id)); //<-findUserById(id));
        return "user";
    }


    @GetMapping("/new")
    public String createUserForm(@ModelAttribute("user") User user, List<Long>ids) {
        List<Role> roles = roleService.getRolesByIds(ids);
        user.setRoles(roles);
        return "new";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);//<-saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/update")
    public String createUpdateForm(@RequestParam(value = "id") List<Long>ids, Model model, long id) {
        List<Role> roles = roleService.getRolesByIds(ids);
        User user = userService.showUserById(id);
        user.setRoles(roles);
        model.addAttribute("user", userService.showUserById(id));
        return "update";
    }

    @PostMapping("/user")
    public String updateUser(@ModelAttribute("user") User user, long id) {
        userService.update(id, user);//<-updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam(value = "id") Long id) {
        userService.delete(id);//<-deleteUser(id);
        return "redirect:/admin";
    }

}
