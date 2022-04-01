package net.artux.nextcrm.controller.users;

import net.artux.nextcrm.controller.BaseEntityController;
import net.artux.nextcrm.model.user.UserCreateDto;
import net.artux.nextcrm.model.user.UserDto;
import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.service.role.RoleService;
import net.artux.nextcrm.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/settings/management/users")
public class UsersController extends BaseEntityController<UserCreateDto, UserDto, UserEntity, UserService> {

    private final RoleService roleService;

    public UsersController(UserService userService, RoleService roleService){
        super("Сотрудники", "settings/management/users", userService);
        this.roleService = roleService;
    }

    @Override
    public String create(Model model) {
        model.addAttribute("roles", roleService.getAll());
        return super.create(model);
    }

    @Override
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model,@PathVariable Long id) {
        model.addAttribute("roles", roleService.getAll());
        return super.edit(model, id);
    }
}
