package net.artux.nextcrm.controller.settings.statuses;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.order.payment.PaymentStatusEntity;
import net.artux.nextcrm.repository.settings.statuses.PaymentStatusRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings/statuses/payments")
public class PaymentStatusController extends BaseRepositoryController<PaymentStatusEntity, PaymentStatusRepository> {

    public PaymentStatusController(PaymentStatusRepository repository){
        super("Статусы - Оплата", "settings/statuses", repository);
    }

}
