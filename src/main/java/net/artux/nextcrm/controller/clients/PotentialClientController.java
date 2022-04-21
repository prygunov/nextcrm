package net.artux.nextcrm.controller.clients;

import net.artux.nextcrm.controller.BaseRepositoryController;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.client.PotentialClientEntity;
import net.artux.nextcrm.repository.ClientRepository;
import net.artux.nextcrm.repository.PotentialClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/potential")
public class PotentialClientController extends BaseRepositoryController<PotentialClientEntity, PotentialClientRepository> {

    public PotentialClientController(PotentialClientRepository repository){
        super("Потенциальные клиенты", "clients/potential", repository);
    }

}
