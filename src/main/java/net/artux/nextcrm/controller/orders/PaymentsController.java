package net.artux.nextcrm.controller.orders;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.order.delivery.DeliveryEntity;
import net.artux.nextcrm.model.order.delivery.DeliveryStatusEntity;
import net.artux.nextcrm.model.order.delivery.DeliveryTypeEntity;
import net.artux.nextcrm.model.order.payment.PaymentEntity;
import net.artux.nextcrm.repository.clients.AddressesRepository;
import net.artux.nextcrm.repository.orders.DeliveryRepository;
import net.artux.nextcrm.repository.orders.DeliveryTypeRepository;
import net.artux.nextcrm.repository.orders.OrdersRepository;
import net.artux.nextcrm.repository.orders.PaymentRepository;
import net.artux.nextcrm.repository.settings.statuses.DeliveryStatusRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentsController extends BaseRepositoryController<PaymentEntity, PaymentRepository> {


    public PaymentsController(PaymentRepository repository) {
        super("Доставки", "orders/delivery", repository);
    }

    @Override
    public Object update(PaymentEntity dto, BindingResult bindingResult,@PathVariable Long id, Model model) throws IllegalAccessException {
        super.update(dto, bindingResult, id, model);
        return redirect("/orders/" + dto.getOrder().getId() + "/edit", model, null);
    }

    @Override
    public Object remove(@PathVariable Long id, Model model) {
        PaymentEntity payment = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return redirect("/orders/" + payment.getOrder().getId() + "/edit", model, null);
    }
}
