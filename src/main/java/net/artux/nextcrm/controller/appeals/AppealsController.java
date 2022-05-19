package net.artux.nextcrm.controller.appeals;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.appeal.AppealEntity;
import net.artux.nextcrm.model.appeal.AppealStatusEntity;
import net.artux.nextcrm.model.appeal.ChannelEntity;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.repository.AppealsRepository;
import net.artux.nextcrm.repository.clients.ClientRepository;
import net.artux.nextcrm.repository.clients.PotentialClientRepository;
import net.artux.nextcrm.repository.settings.management.ChannelRepository;
import net.artux.nextcrm.repository.settings.management.UsersRepository;
import net.artux.nextcrm.repository.settings.statuses.AppealStatusRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/appeals")
public class AppealsController extends BaseRepositoryController<AppealEntity, AppealsRepository> {

    private final AppealStatusRepository statusRepository;
    private final PotentialClientRepository potentialClientRepository;
    private final ChannelRepository channelRepository;
    private final ClientRepository clientRepository;

    public AppealsController(AppealsRepository repository, AppealStatusRepository statusRepository, PotentialClientRepository potentialClientRepository, ChannelRepository channelRepository, UsersRepository usersRepository, ClientRepository clientRepository){
        super("Обращения", "appeals", repository);
        this.statusRepository = statusRepository;
        this.potentialClientRepository = potentialClientRepository;
        this.channelRepository = channelRepository;
        this.clientRepository = clientRepository;
    }

    @RequestMapping(value = "/createPotential", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute AppealEntity object, Model model){
        if (object.getClient() == null && !object.getPotentialClient().getName().isBlank()) {
            object.setPotentialClient(potentialClientRepository
                    .save(object.getPotentialClient()));
        }
        object.setTime(new Date());
        repository.save(object);
        return new ModelAndView("redirect:" + getPageUrl());
    }

    @ModelAttribute("statuses")
    List<AppealStatusEntity> getStatuses(){
        return statusRepository.findAll();
    }

    @ModelAttribute("channels")
    List<ChannelEntity> getChannels(){
        return channelRepository.findAll();
    }

    @ModelAttribute("clients")
    List<ClientEntity> getClients(){
        return clientRepository.findAll();
    }

}
