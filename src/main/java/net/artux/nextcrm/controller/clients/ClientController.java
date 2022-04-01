package net.artux.nextcrm.controller.clients;

import net.artux.nextcrm.controller.BaseEntityController;
import net.artux.nextcrm.model.client.ClientDto;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clients")
public class ClientController extends BaseEntityController<ClientDto, ClientDto, ClientEntity, ClientService> {

    public ClientController(ClientService clientService){
        super("Клиенты", "clients", clientService);
    }

}
