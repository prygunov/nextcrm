package net.artux.nextcrm.controller.settings.statuses;

import net.artux.nextcrm.controller.BaseRepositoryController;
import net.artux.nextcrm.model.order.OrderStatusEntity;
import net.artux.nextcrm.repository.settings.statuses.OrderStatusRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings/statuses/orders")
public class OrderStatusController extends BaseRepositoryController<OrderStatusEntity, OrderStatusRepository> {

    public OrderStatusController(OrderStatusRepository repository){
        super("Статусы - Заказы", "settings/statuses", repository);
    }

}
