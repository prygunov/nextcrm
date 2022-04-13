package net.artux.nextcrm.controller.orders;

import net.artux.nextcrm.controller.BaseController;
import net.artux.nextcrm.service.orders.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrdersController extends BaseController {

    private final OrdersService service;

    public OrdersController(OrdersService service){
        super("Заказы");
        this.service = service;
    }

    @Override
    protected Object getHome(Model model) {
        return pageWithContent("orders/menu", model);
    }
}
