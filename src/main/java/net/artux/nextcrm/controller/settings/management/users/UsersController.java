package net.artux.nextcrm.controller.settings.management.users;

import net.artux.nextcrm.controller.BaseEntityController;
import net.artux.nextcrm.model.role.RoleEntity;
import net.artux.nextcrm.model.user.UserCreateDto;
import net.artux.nextcrm.model.user.UserDto;
import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.repository.settings.management.RoleRepository;
import net.artux.nextcrm.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/settings/management/users")
public class UsersController extends BaseEntityController<UserCreateDto, UserDto, UserEntity, UserService> {

    private final RoleRepository roleRepository;

    public UsersController(UserService userService, RoleRepository roleRepository){
        super("Сотрудники", "settings/management/users", userService);
        this.roleRepository = roleRepository;
    }

    @ModelAttribute("roles")
    public List<RoleEntity> getRoles(){
        return roleRepository.findAll();
    }
}
