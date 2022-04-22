package net.artux.nextcrm.controller.clients;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.repository.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clients")
public class ClientController extends BaseRepositoryController<ClientEntity, ClientRepository> {

    public ClientController(ClientRepository repository){
        super("Клиенты", "clients", repository);
    }

}
