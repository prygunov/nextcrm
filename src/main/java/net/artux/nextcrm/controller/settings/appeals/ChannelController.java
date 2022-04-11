package net.artux.nextcrm.controller.settings.appeals;

import net.artux.nextcrm.controller.BaseRepositoryController;
import net.artux.nextcrm.model.appeal.ChannelEntity;
import net.artux.nextcrm.model.role.RoleEntity;
import net.artux.nextcrm.repository.settings.management.ChannelRepository;
import net.artux.nextcrm.repository.settings.management.RoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/settings/channels")
public class ChannelController extends BaseRepositoryController<ChannelEntity, ChannelRepository> {

    public ChannelController(ChannelRepository repository) {
        super("Каналы продаж", "settings/appeals/channels", repository);
    }


}
