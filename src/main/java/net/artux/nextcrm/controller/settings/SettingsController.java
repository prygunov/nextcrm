package net.artux.nextcrm.controller.settings;

import net.artux.nextcrm.controller.BaseController;
import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.model.user.UserDto;
import net.artux.nextcrm.service.role.RoleService;
import net.artux.nextcrm.service.user.UserService;
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

}
