package net.artux.nextcrm.controller.settings.management.users;

import net.artux.nextcrm.controller.util.BaseEntityController;
import net.artux.nextcrm.model.role.RoleEntity;
import net.artux.nextcrm.model.user.UserCreateDto;
import net.artux.nextcrm.model.user.UserDto;
import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.repository.orders.OrdersRepository;
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
    private final OrdersRepository ordersRepository;

    public UsersController(UserService userService, RoleRepository roleRepository, OrdersRepository ordersRepository){
        super("Сотрудники", "settings/management/users", userService);
        this.roleRepository = roleRepository;
        this.ordersRepository = ordersRepository;
    }

    @ModelAttribute("roles")
    public List<RoleEntity> getRoles(){
        return roleRepository.findAll();
    }

    @Override
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Long id) {
        model.addAttribute("orders", ordersRepository.findAllByEmployeeId(id));
        return super.edit(model, id);
    }
}
