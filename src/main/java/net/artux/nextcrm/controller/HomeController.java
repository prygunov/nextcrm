package net.artux.nextcrm.controller;

import net.artux.nextcrm.controller.util.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

    public HomeController() {
        super("Главная страница");
    }

    @GetMapping
    public String getHome(Model model){
        return pageWithContent("welcome", model);
    }

}
