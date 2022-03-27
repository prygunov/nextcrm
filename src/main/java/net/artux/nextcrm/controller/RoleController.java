package net.artux.nextcrm.controller;

import net.artux.nextcrm.model.RoleEntity;
import net.artux.nextcrm.repository.BasicRepository;
import net.artux.nextcrm.service.RoleService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/settings/management/roles")
public class RoleController extends BasicController<RoleEntity>{

    private final RoleService roleService;

    public RoleController(JdbcTemplate template, RoleService roleService) {
        super(new BasicRepository<>(template, RoleEntity.class));
        this.roleService = roleService;
    }

    @RequestMapping(value = "/{action}/{id}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable Long id, @PathVariable String action){
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

        return new ModelAndView("forward:/settings/management");
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@PathVariable Long id, @PathVariable String action){
       

        return new ModelAndView("forward:/settings/management");
    }

}
