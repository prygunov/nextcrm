package net.artux.nextcrm.controller.clients;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.appeal.ChannelEntity;
import net.artux.nextcrm.model.client.PotentialClientEntity;
import net.artux.nextcrm.repository.clients.PotentialClientRepository;
import net.artux.nextcrm.repository.settings.management.ChannelRepository;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/potential")
public class PotentialClientController extends BaseRepositoryController<PotentialClientEntity, PotentialClientRepository> {

    private final ChannelRepository channelRepository;

    public PotentialClientController(PotentialClientRepository repository, ChannelRepository channelRepository){
        super("Потенциальные клиенты", "clients/potential", repository);
        this.channelRepository = channelRepository;
    }

    @RequestMapping("/filter")
    public Object filter(@RequestParam(value = "search", defaultValue = "", required = false) String query,
                         @RequestParam(value = "channel", defaultValue = "-1", required = false) Integer channelId,
                         @RequestParam(value = "active", defaultValue = "false", required = false) boolean active, Model model){

        model.addAttribute("objects", repository.filter('%'+query+'%', channelId, active));
        model.addAttribute("search", query);
        model.addAttribute("channelId", channelId);
        model.addAttribute("active", active);
        return getHome(model);
    }

    @ModelAttribute("channels")
    private List<ChannelEntity> getChannels(){
        return channelRepository.findAll();
    }

}
