package net.artux.nextcrm.controller.settings.statuses;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.order.payment.PaymentTypeEntity;
import net.artux.nextcrm.model.task.TaskStatusEntity;
import net.artux.nextcrm.repository.orders.PaymentTypesRepository;
import net.artux.nextcrm.repository.settings.statuses.TaskStatusRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment/types")
public class PaymentTypeController extends BaseRepositoryController<PaymentTypeEntity, PaymentTypesRepository> {

    public PaymentTypeController(PaymentTypesRepository repository){
        super("Оплата - Типы", "settings/payment/types", repository);
    }

}
