package net.artux.nextcrm.controller.orders;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.order.OrderFilter;
import net.artux.nextcrm.model.order.OrderStatusEntity;
import net.artux.nextcrm.model.order.delivery.DeliveryStatusEntity;
import net.artux.nextcrm.model.order.delivery.DeliveryTypeEntity;
import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.repository.clients.ClientRepository;
import net.artux.nextcrm.repository.orders.DeliveryTypeRepository;
import net.artux.nextcrm.repository.orders.OrdersRepository;
import net.artux.nextcrm.repository.settings.management.UsersRepository;
import net.artux.nextcrm.repository.settings.statuses.DeliveryStatusRepository;
import net.artux.nextcrm.repository.settings.statuses.OrderStatusRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController extends BaseRepositoryController<OrderEntity, OrdersRepository> {


    private final OrderStatusRepository statusRepository;
    private final ClientRepository clientRepository;
    private final UsersRepository usersRepository;
    private final DeliveryStatusRepository deliveryStatusRepository;
    private final DeliveryTypeRepository deliveryTypeRepository;

    public OrdersController(OrdersRepository repository, OrderStatusRepository statusRepository, ClientRepository clientRepository, UsersRepository usersRepository, DeliveryStatusRepository deliveryStatusRepository, DeliveryTypeRepository deliveryTypeRepository){
        super("Заказы", "orders", repository);
        this.statusRepository = statusRepository;
        this.clientRepository = clientRepository;
        this.usersRepository = usersRepository;
        this.deliveryStatusRepository = deliveryStatusRepository;
        this.deliveryTypeRepository = deliveryTypeRepository;
    }

    @Override
    public String getHome(Model model) {
        model.addAttribute("object", new OrderFilter());
        model.addAttribute("objects", repository.findAll());
        return pageWithContent("orders/menu", model);
    }

    @ModelAttribute("clients")
    private List<ClientEntity> getClients(){
        return clientRepository.findAll();
    }

    @ModelAttribute("statuses")
    private List<OrderStatusEntity> getStatuses(){
        return statusRepository.findAll();
    }

    @ModelAttribute("deliveryTypes")
    private List<DeliveryTypeEntity> getDeliveryTypes(){
        return deliveryTypeRepository.findAll();
    }

    @ModelAttribute("deliveryStatuses")
    private List<DeliveryStatusEntity> getDeliveryStatuses(){
        return deliveryStatusRepository.findAll();
    }

    @ModelAttribute("employees")
    private List<UserEntity> getUsers(){
        return usersRepository.findAll();
    }
}
