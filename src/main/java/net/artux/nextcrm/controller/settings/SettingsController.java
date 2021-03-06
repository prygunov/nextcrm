package net.artux.nextcrm.controller.settings;

import net.artux.nextcrm.controller.util.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/settings")
public class SettingsController extends BaseController {

    public SettingsController() {
        super("Настройки");
    }

    @GetMapping
    public String getHome(Model model){
        return pageWithContent("settings/menu", model);
    }

    @GetMapping("/statuses")
    public String getStatuses(Model model){
        return pageWithContent("settings/statuses/menu", model);
    }
}
