package net.artux.nextcrm.controller.clients;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.repository.clients.ClientRepository;
import net.artux.nextcrm.repository.orders.OrdersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController extends BaseRepositoryController<ClientEntity, ClientRepository> {

    private final OrdersRepository ordersRepository;

    public ClientController(ClientRepository repository, OrdersRepository ordersRepository){
        super("Клиенты", "clients", repository);
        this.ordersRepository = ordersRepository;
    }

    @Override
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Long id) {
        model.addAttribute("orders", ordersRepository.findAllByClientId(id));
        return super.edit(model, id);
    }

}
