package net.artux.nextcrm.controller.settings;

import net.artux.nextcrm.controller.BaseController;
import net.artux.nextcrm.controller.BaseEntityController;
import net.artux.nextcrm.model.role.RoleDto;
import net.artux.nextcrm.model.role.RoleEntity;
import net.artux.nextcrm.service.role.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/settings/management/roles")
public class RoleController extends BaseEntityController<RoleDto, RoleDto, RoleEntity, RoleService> {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        super("Роли", "settings/management/roles", roleService);
        this.roleService = roleService;
    }

    @RequestMapping(value = "/{action}/{id}", method = RequestMethod.GET)
    public ModelAndView updateRole(@PathVariable Long id, @PathVariable String action){
        roleService.changeRole(id, action);
        return new ModelAndView("redirect:/settings/management/roles");
    }
}
