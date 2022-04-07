package net.artux.nextcrm.controller.settings.statuses;

import net.artux.nextcrm.controller.BaseRepositoryController;
import net.artux.nextcrm.model.order.delivery.DeliveryEntity;
import net.artux.nextcrm.model.order.delivery.DeliveryStatusEntity;
import net.artux.nextcrm.model.task.TaskStatusEntity;
import net.artux.nextcrm.repository.settings.statuses.DeliveryStatusRepository;
import net.artux.nextcrm.repository.settings.statuses.TaskStatusRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings/statuses/deliveries")
public class DeliveryStatusController extends BaseRepositoryController<DeliveryStatusEntity, DeliveryStatusRepository> {

    public DeliveryStatusController(DeliveryStatusRepository repository){
        super("Статусы - Доставка", "settings/statuses", repository);
    }

}
