package net.artux.nextcrm.controller.settings.appeals;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.appeal.ChannelEntity;
import net.artux.nextcrm.repository.settings.management.ChannelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings/channels")
public class ChannelController extends BaseRepositoryController<ChannelEntity, ChannelRepository> {

    public ChannelController(ChannelRepository repository) {
        super("Каналы продаж", "settings/appeals/channels", repository);
    }


}
