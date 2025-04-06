package edu.kata.spring_boot_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kata.spring_boot_security.entity.User;
import edu.kata.spring_boot_security.service.RoleService;
import edu.kata.spring_boot_security.service.UserService;

@Controller
@RequestMapping(path = {"/admin"})
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(
            UserService UserService,
            RoleService roleService) {
        this.userService = UserService;
        this.roleService = roleService;
    }

    @GetMapping(path = {"", "/", "/all", "/all/"})
    public String getUsersListWithFormForAddUser(
            ModelMap modelMap) {
        modelMap.addAttribute("userList", userService.getUserList());
        return "admin/user_list";
    }

    @GetMapping(path = {"/add_user/"})
    public String getAddUserForm(
            ModelMap modelMap) {
        User user = new User();
        modelMap.addAttribute("addedUser", user);
        modelMap.addAttribute("allRoles", roleService.getRolesList());
        return "admin/add_user_form";
    }

    @GetMapping(path = {"/update/"})
    public String getUsersListWithFormForUpdateUser(
            @RequestParam(name = "id") Long userId,
            ModelMap modelMap) {
        modelMap.addAttribute("beingUpdateUser", userService.getUserById(userId).orElse(null));
        modelMap.addAttribute("userList", userService.getUserList());
        return "admin/user_list";
    }

    @PostMapping(path = {"/add/"})
    public String addUser(
            User user,
            ModelMap modelMap) {
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @PostMapping(path = {"/delete/"})
    public String deleteUserById(
            User user,
            ModelMap modelMap) {
        userService.deleteUser(user);
        return "redirect:/admin/";
    }

    @PostMapping(path = {"/update/"})
    public String updateUser(
            User user,
            ModelMap modelMap) {
        userService.updateUser(user);
        return "redirect:/admin/";
    }
}
