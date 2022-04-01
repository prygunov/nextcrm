package net.artux.nextcrm.controller;

import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.user.UserCreateDto;
import net.artux.nextcrm.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String getRegistrationPage(Model model){
        model.addAttribute("user", new UserCreateDto());
        return "auth/registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute UserCreateDto user, Model model){
        userService.create(user);
        model.addAttribute("success", true);
        return "auth/login";
    }

}
