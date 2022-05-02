package net.artux.nextcrm.controller.util;

import net.artux.nextcrm.repository.orders.OrdersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/printing")
public class PrintingController {

    private final OrdersRepository ordersRepository;

    public PrintingController(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @GetMapping("/order/{id}")
    public String printList(@PathVariable("id") Long id, Model model){
        model.addAttribute("order", new OrderPrint(ordersRepository
                .findById(id).orElseThrow()));
        return "printing/order";
    }

}
