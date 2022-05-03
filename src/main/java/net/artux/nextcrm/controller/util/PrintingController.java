package net.artux.nextcrm.controller.util;

import net.artux.nextcrm.repository.CompanyInfoRepository;
import net.artux.nextcrm.repository.orders.OrdersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RequestMapping("/printing")
public class PrintingController {

    private final OrdersRepository ordersRepository;
    private final CompanyInfoRepository companyInfoRepository;

    public PrintingController(OrdersRepository ordersRepository, CompanyInfoRepository companyInfoRepository) {
        this.ordersRepository = ordersRepository;
        this.companyInfoRepository = companyInfoRepository;
    }

    @GetMapping("/order/{id}")
    public String printList(@PathVariable("id") Long id, Model model){
        model.addAttribute("order", new OrderPrint(ordersRepository
                .findById(id).orElseThrow()));
        return "printing/order";
    }

    @GetMapping("/order/{id}/check")
    public String printCheck(@PathVariable("id") Long id, Model model) throws RuntimeException {
        model.addAttribute("company", companyInfoRepository.findFirst().orElseThrow(() -> new RuntimeException("Не задана информация о комании, необходимо задать ее в настройках.")));
        model.addAttribute("order", new OrderPrint(ordersRepository
                .findById(id).orElseThrow()));
        return "printing/check";
    }

    @GetMapping("/order/{id}/account")
    public String printAccount(@PathVariable("id") Long id, Model model) throws RuntimeException {
        model.addAttribute("company", companyInfoRepository.findFirst().orElseThrow(() -> new RuntimeException("Не задана информация о комании, необходимо задать ее в настройках.")));
        model.addAttribute("order", new OrderPrint(ordersRepository
                .findById(id).orElseThrow()));
        return "printing/account";
    }

}
