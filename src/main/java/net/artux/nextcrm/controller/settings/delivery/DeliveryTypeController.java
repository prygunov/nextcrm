package net.artux.nextcrm.controller.settings.delivery;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.order.delivery.DeliveryTypeEntity;
import net.artux.nextcrm.repository.orders.DeliveryTypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings/delivery")
public class DeliveryTypeController extends BaseRepositoryController<DeliveryTypeEntity, DeliveryTypeRepository> {

    public DeliveryTypeController(DeliveryTypeRepository repository){
        super("Доставка - Типы", "settings/delivery", repository);
    }

}
