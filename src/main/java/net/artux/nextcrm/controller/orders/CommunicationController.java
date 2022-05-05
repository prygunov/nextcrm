package net.artux.nextcrm.controller.orders;

import net.artux.nextcrm.controller.util.BaseController;
import net.artux.nextcrm.model.order.*;
import net.artux.nextcrm.repository.orders.CallRepository;
import net.artux.nextcrm.repository.orders.MessageRepository;
import net.artux.nextcrm.repository.orders.OrderEventRepository;
import net.artux.nextcrm.repository.orders.OrdersRepository;
import net.artux.nextcrm.repository.settings.management.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Controller
@RequestMapping("/communication")
public class CommunicationController extends BaseController {

    private final CallRepository callRepository;
    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;
    private final MessageRepository messageRepository;

    public CommunicationController(CallRepository callRepository, OrdersRepository ordersRepository, UsersRepository usersRepository, MessageRepository messageRepository) {
        super("Коммуникации");
        this.callRepository = callRepository;
        this.ordersRepository = ordersRepository;
        this.usersRepository = usersRepository;
        this.messageRepository = messageRepository;
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

    @GetMapping("/mail")
    public Object createMail(@RequestParam("orderId") Long orderId, Model model){
        OrderEntity order = ordersRepository.findById(orderId).orElseThrow();

        model.addAttribute("orderId", orderId);
        model.addAttribute("to", order.getClient().getEmail());
        model.addAttribute("subject", "Уведомление по заказу #" + orderId);

        return pageWithContent("orders/mail", model);
    }

    @PostMapping("/mail")
    public ResponseEntity autoCreate(@RequestParam("orderId") Long orderId, @RequestParam("to") String email, @RequestParam("body") String body, @RequestParam("subject") String subject){
        OrderEntity order = ordersRepository.findById(orderId).orElseThrow();

        MessageEntity message = new MessageEntity();
        message.setAuthor(usersRepository.findByLogin(SecurityContextHolder
                .getContext().getAuthentication().getName()).get());
        message.setTime(new Date());
        message.setOrder(order);
        message.setTarget(email);
        message.setMessage(body);
        message.setSubject(subject);
        message.setType(MessageType.EMAIL);
        messageRepository.save(message);

        body = URLEncoder.encode(body, StandardCharsets.UTF_8);
        subject = URLEncoder.encode(subject, StandardCharsets.UTF_8);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("mailto:" + email + "?body=" + body + "&subject=" + subject))
                .build();
    }

    @Override
    protected Object getHome(Model model) {
        return redirect("/", model, null);
    }
}
