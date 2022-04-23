package net.artux.nextcrm.controller.orders;

import net.artux.nextcrm.model.order.CallEntity;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.order.OrderEventEntity;
import net.artux.nextcrm.repository.orders.CallRepository;
import net.artux.nextcrm.repository.orders.OrderEventRepository;
import net.artux.nextcrm.repository.orders.OrdersRepository;
import net.artux.nextcrm.repository.settings.management.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.util.Date;

@Controller
@RequestMapping("/communication")
public class CommunicationController{

    private final CallRepository callRepository;
    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;

    public CommunicationController(OrderEventRepository repository, CallRepository callRepository, OrdersRepository ordersRepository, UsersRepository usersRepository) {
        this.callRepository = callRepository;
        this.ordersRepository = ordersRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/tel")
    public ResponseEntity autoCreate(@RequestParam("orderId") Long orderId, @RequestParam("number") String number){
        OrderEntity order = ordersRepository.findById(orderId).get();

        CallEntity callEntity = new CallEntity();
        callEntity.setAuthor(usersRepository.findByLogin(SecurityContextHolder
                .getContext().getAuthentication().getName()).get());
        callEntity.setIncome(false);
        callEntity.setTime(new Date());
        callEntity.setNumber(number);
        callEntity.setOrder(order);
        callRepository.save(callEntity);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("tel:" + number))
                .build();
    }

    @GetMapping("/mailto")
    public ResponseEntity autoCreate(@RequestParam("orderId") Long orderId, @RequestParam("mailto") String email, @RequestParam("body") String body, @RequestParam("subject") String subject){
        OrderEntity order = ordersRepository.findById(orderId).get();

        CallEntity callEntity = new CallEntity();
        callEntity.setAuthor(usersRepository.findByLogin(SecurityContextHolder
                .getContext().getAuthentication().getName()).get());
        callEntity.setIncome(false);
        callEntity.setTime(new Date());
        callEntity.setOrder(order);
        callRepository.save(callEntity);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("mailto:" + email + "?body=" + body + "&subject=" + subject))
                .build();
    }

}
