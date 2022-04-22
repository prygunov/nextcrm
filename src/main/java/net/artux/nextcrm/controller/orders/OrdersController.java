package net.artux.nextcrm.controller.orders;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.order.OrderFilter;
import net.artux.nextcrm.repository.OrdersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrdersController extends BaseRepositoryController<OrderEntity, OrdersRepository> {


    public OrdersController(OrdersRepository repository){
        super("Заказы", "orders", repository);
    }

    @Override
    public String getHome(Model model) {
        model.addAttribute("object", new OrderFilter());
        model.addAttribute("objects", repository.findAll());
        return pageWithContent("orders/menu", model);
    }
}
