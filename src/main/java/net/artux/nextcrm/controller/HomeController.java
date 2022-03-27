package net.artux.nextcrm.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String getHome(Model model){
        model.addAttribute("login", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("title", "Главная страница");
        return "home";
    }

}
