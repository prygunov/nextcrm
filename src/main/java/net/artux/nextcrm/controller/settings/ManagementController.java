package net.artux.nextcrm.controller.settings;

import net.artux.nextcrm.controller.BaseController;
import net.artux.nextcrm.model.user.UserDto;
import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.service.role.RoleService;
import net.artux.nextcrm.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/settings/management")
public class ManagementController extends BaseController {

    private final UserService userService;
    private final RoleService roleService;

    public ManagementController(UserService userService, RoleService roleService) {
        super("Настройки");
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getHome(Model model){
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAll());
        return pageWithContent("settings/management/management", model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getUser(Model model, @PathVariable Long id){
        UserEntity userEntity = userService.getUser(id);

        model.addAttribute("title", "Сотрудник " + userEntity.getLastname() + " " + userEntity.getFirstname());
        model.addAttribute("roles", roleService.getAll());
        model.addAttribute("user", userEntity);
        return pageWithContent("settings/management/edit-user", model);
    }

    @PostMapping
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String setUser(@ModelAttribute UserDto user, @PathVariable Long id, Model model){
        userService.updateUser(id, user);

        return getHome(model);
    }

}
