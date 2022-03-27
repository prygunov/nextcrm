package net.artux.nextcrm.controller;

import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.UserEntity;
import net.artux.nextcrm.model.dto.UserDto;
import net.artux.nextcrm.service.RoleService;
import net.artux.nextcrm.service.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettingsController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public String getHome(Model model){
        model.addAttribute("login", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("title", "Настройки");
        model.addAttribute("content", "settings");
        return "home";
    }

    @GetMapping
    @RequestMapping("/management")
    public String getManagement(Model model){
        model.addAttribute("login", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("title", "Сотрудники и роли");
        model.addAttribute("content", "management");
        model.addAttribute("users", userService.getAllUsers());
        return "home";
    }

    @RequestMapping(value = "/management/{id}", method = RequestMethod.GET)
    public String getUser(Model model, @PathVariable Long id){
        UserEntity userEntity = userService.getUser(id);

        model.addAttribute("login", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("title", "Сотрудник " + userEntity.getLastname() + " " + userEntity.getFirstname());
        model.addAttribute("content", "edit-user");
        model.addAttribute("roles", roleService.getAll());
        model.addAttribute("user", userEntity);
        return "home";
    }

    @PostMapping
    @RequestMapping(value = "/management/{id}", method = RequestMethod.POST)
    public String setUser(@ModelAttribute UserDto user, @PathVariable Long id, Model model){
        userService.updateUser(id, user);

        return getManagement(model);
    }

}
