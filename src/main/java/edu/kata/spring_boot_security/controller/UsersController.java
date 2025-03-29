package edu.kata.spring_boot_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import edu.kata.spring_boot_security.entity.UserData;
import edu.kata.spring_boot_security.service.UserDataService;

@Controller
@RequestMapping(path = {"/","/users"})
public class UsersController {

    private final UserDataService userDataService;

    public UsersController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @GetMapping(path = {"", "/", "/all"})
    public String getUsersListWithFormForAddUser(ModelMap modelMap) {
        modelMap.addAttribute("beingUpdateUser", new UserData());
        modelMap.addAttribute("userDataList", userDataService.getUserDataList());
        return "users";
    }

    @PostMapping(path = {"/delete"})
    public String deleteUserById(
            @RequestParam(name = "id") Long id,
            ModelMap modelMap) {
        userDataService.deleteUserDataById(id);
        return "redirect:..";
    }

    @GetMapping(path = {"/update"})
    public String getUsersListWithFormForUpdateUser(
            @RequestParam(name = "update_user_id") Long beingUpdateUserId,
            ModelMap modelMap) {
        Optional<UserData> beingUpdateUser = userDataService.getUserDataById(beingUpdateUserId);
        if (beingUpdateUser.isEmpty()) {
            throw new IllegalArgumentException("No user found with requested ID");
        }
        modelMap.addAttribute("beingUpdateUser", beingUpdateUser.get());
        modelMap.addAttribute("userDataList", userDataService.getUserDataList());
        return "users";
    }

    @PostMapping(path = {"/add"})
    public String addUser(UserData userData, ModelMap modelMap) {
        userDataService.addUserData(userData);
        return "redirect:..";
    }

    @PostMapping(path = {"/update"})
    public String updateUser(UserData userData, ModelMap modelMap) {
        userDataService.updateUserData(userData);
        return "redirect:..";
    }
}
