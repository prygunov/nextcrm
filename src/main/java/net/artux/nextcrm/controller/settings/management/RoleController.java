package net.artux.nextcrm.controller.settings.management;

import net.artux.nextcrm.controller.BaseRepositoryController;
import net.artux.nextcrm.model.role.RoleEntity;
import net.artux.nextcrm.repository.settings.management.RoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/settings/management/roles")
public class RoleController extends BaseRepositoryController<RoleEntity, RoleRepository> {

    public RoleController(RoleRepository repository) {
        super("Роли", "settings/management/roles", repository);
    }

    @RequestMapping(value = "/{action}/{id}", method = RequestMethod.GET)
    public ModelAndView updateRole(@PathVariable Long id, @PathVariable String action){
        RoleEntity entity = repository.getById(id);
        switch (action){
            case "tasks":
                entity.setTasks(!entity.isTasks());
                break;
            case "orders":
                entity.setOrders(!entity.isOrders());
                break;
            case "clients":
                entity.setClients(!entity.isClients());
                break;
        }
        repository.save(entity);
        return new ModelAndView("redirect:/settings/management/roles");
    }
}
