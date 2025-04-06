package edu.kata.spring_boot_security.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kata.spring_boot_security.entity.User;

@Controller
@RequestMapping(path = {"/user"})
public class UserController {

    @GetMapping(path = {"", "/"})
    public String getUserData(
            @AuthenticationPrincipal User user,
            ModelMap modelMap) {
        modelMap.addAttribute("user", user);
        return "user/user_data";
    }
}
