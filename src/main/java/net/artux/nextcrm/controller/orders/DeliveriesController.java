package net.artux.nextcrm.controller.orders;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.address.AddressEntity;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.order.OrderEventEntity;
import net.artux.nextcrm.model.order.delivery.DeliveryEntity;
import net.artux.nextcrm.model.order.delivery.DeliveryStatusEntity;
import net.artux.nextcrm.model.order.delivery.DeliveryTypeEntity;
import net.artux.nextcrm.repository.clients.AddressesRepository;
import net.artux.nextcrm.repository.orders.DeliveryRepository;
import net.artux.nextcrm.repository.orders.DeliveryTypeRepository;
import net.artux.nextcrm.repository.orders.OrdersRepository;
import net.artux.nextcrm.repository.settings.management.UsersRepository;
import net.artux.nextcrm.repository.settings.statuses.DeliveryStatusRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/deliveries")
public class DeliveriesController extends BaseRepositoryController<DeliveryEntity, DeliveryRepository>{

    private final DeliveryStatusRepository deliveryStatusRepository;
    private final DeliveryTypeRepository deliveryTypeRepository;
    private final OrdersRepository ordersRepository;
    private final AddressesRepository addressesRepository;

    public DeliveriesController(DeliveryRepository repository, DeliveryStatusRepository deliveryStatusRepository, DeliveryTypeRepository deliveryTypeRepository, OrdersRepository ordersRepository, AddressesRepository addressesRepository) {
        super("Доставки", "orders/delivery", repository);
        this.deliveryStatusRepository = deliveryStatusRepository;
        this.deliveryTypeRepository = deliveryTypeRepository;
        this.ordersRepository = ordersRepository;
        this.addressesRepository = addressesRepository;
    }

    @GetMapping("/auto/create")
    public Object autoCreate(Model model, @RequestParam("orderId") Long orderId){
        OrderEntity order = ordersRepository.findById(orderId).get();
        DeliveryEntity deliveryEntity = new DeliveryEntity();
        deliveryEntity.setDate(new Date());
        deliveryEntity.setAddress(order.getClient().getDefaultAddress());
        deliveryEntity.setOrder(order);

        model.addAttribute("object", deliveryEntity);
        model.addAttribute(deliveryEntity);

        return pageWithContent(folder + "/edit", model);
    }


    @RequestMapping(value = "/create", params = {"q", "search"})
    public Object findAddresses(@ModelAttribute DeliveryEntity delivery, RedirectAttributes redirectAttributes,@RequestParam("q") String query,  Model model) {
        model.addAttribute("addresses", addressesRepository.findAddresses('%' + query + '%'));
        model.addAttribute("q", query);

        return redirect(getPageUrl() + "create#searchAddress", model, redirectAttributes);
    }

    @RequestMapping(value = "/create", params = "addressId")
    public Object addAddress(@ModelAttribute DeliveryEntity delivery, RedirectAttributes redirectAttributes, @RequestParam("addressId") Long addressId, Model model) {
        delivery.setAddress(addressesRepository.findById(addressId).orElseThrow());

        return redirect(getPageUrl() + "create#searchAddress", model, redirectAttributes);
    }

    @RequestMapping(value = "/changeAddress")
    public Object setDeliveryAddress(@RequestParam("addressId") Long addressId, Model model, @RequestParam("deliveryId") Long deliveryId){
        DeliveryEntity delivery = repository.findById(deliveryId).orElseThrow();
        delivery.setAddress(addressesRepository.findById(addressId).orElseThrow());
        repository.save(delivery);
        return redirect("/orders/" + delivery.getOrder().getId() + "/edit", model, null);
    }

    @ModelAttribute("types")
    List<DeliveryTypeEntity> getTypes(){
        return deliveryTypeRepository.findAll();
    }

    @ModelAttribute("statuses")
    List<DeliveryStatusEntity> getStatuses(){
        return deliveryStatusRepository.findAll();
    }

    @ModelAttribute("orders")
    List<OrderEntity> getOrders(){
        return ordersRepository.findAllByStatus_EndingIsFalse();
    }

}
