package net.artux.nextcrm.controller.orders;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.order.OrderEventEntity;
import net.artux.nextcrm.repository.orders.OrderEventRepository;
import net.artux.nextcrm.repository.orders.OrdersRepository;
import net.artux.nextcrm.repository.settings.management.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/orders/events")
public class OrderEventsController extends BaseRepositoryController<OrderEventEntity, OrderEventRepository>{

    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;

    public OrderEventsController(OrderEventRepository repository, OrdersRepository ordersRepository, UsersRepository usersRepository) {
        super("Редактирование события", "orders/events", repository);

        this.ordersRepository = ordersRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/auto/create")
    public ModelAndView autoCreate(@RequestParam("orderId") Long orderId){
        OrderEntity order = ordersRepository.findById(orderId).get();
        OrderEventEntity eventEntity = new OrderEventEntity();
        eventEntity.setAuthor(usersRepository.findByLogin(SecurityContextHolder
                .getContext().getAuthentication().getName()).get());
        eventEntity.setTime(new Date());
        eventEntity.setOrder(order);
        eventEntity.setContent("Описание не задано");
        eventEntity = repository.save(eventEntity);

        order.getEvents().add(eventEntity);
        ordersRepository.save(order);

        return new ModelAndView("redirect:" + "/orders/"+orderId+"/edit");
    }

    @Override
    protected Object defaultPage(Model model) {
        OrderEventEntity commentEntity = (OrderEventEntity) model.getAttribute("orderEventEntity");
        return new ModelAndView("redirect:" + "/orders/"+commentEntity.getOrder().getId()+"/edit");
    }
}
