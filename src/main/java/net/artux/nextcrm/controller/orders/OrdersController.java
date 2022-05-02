package net.artux.nextcrm.controller.orders;

import net.artux.nextcrm.controller.util.BaseController;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.order.OrderFilter;
import net.artux.nextcrm.model.order.OrderStatusEntity;
import net.artux.nextcrm.model.order.delivery.DeliveryStatusEntity;
import net.artux.nextcrm.model.order.delivery.DeliveryTypeEntity;
import net.artux.nextcrm.model.order.goods.GoodEntity;
import net.artux.nextcrm.model.order.payment.PaymentEntity;
import net.artux.nextcrm.model.order.payment.PaymentStatusEntity;
import net.artux.nextcrm.model.order.payment.PaymentTypeEntity;
import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.repository.clients.ClientRepository;
import net.artux.nextcrm.repository.orders.DeliveryTypeRepository;
import net.artux.nextcrm.repository.orders.OrdersRepository;
import net.artux.nextcrm.repository.orders.PaymentRepository;
import net.artux.nextcrm.repository.orders.PaymentTypesRepository;
import net.artux.nextcrm.repository.settings.goods.GoodsRepository;
import net.artux.nextcrm.repository.settings.management.UsersRepository;
import net.artux.nextcrm.repository.settings.statuses.DeliveryStatusRepository;
import net.artux.nextcrm.repository.settings.statuses.OrderStatusRepository;
import net.artux.nextcrm.repository.settings.statuses.PaymentStatusRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController extends BaseController {

    private final OrdersRepository repository;
    private final OrderStatusRepository statusRepository;
    private final GoodsRepository goodsRepository;
    private final PaymentRepository paymentRepository;
    private final ClientRepository clientRepository;
    private final UsersRepository usersRepository;
    private final DeliveryStatusRepository deliveryStatusRepository;
    private final DeliveryTypeRepository deliveryTypeRepository;
    private final PaymentStatusRepository paymentStatusRepository;
    private final PaymentTypesRepository paymentTypesRepository;
    private final String folder;

    public OrdersController(OrdersRepository repository, OrderStatusRepository statusRepository, GoodsRepository goodsRepository, PaymentRepository paymentRepository, ClientRepository clientRepository, UsersRepository usersRepository, DeliveryStatusRepository deliveryStatusRepository, DeliveryTypeRepository deliveryTypeRepository, PaymentStatusRepository paymentStatusRepository, PaymentTypesRepository paymentTypesRepository){
        super("Заказы");
        folder = "orders";

        this.paymentRepository = paymentRepository;
        this.paymentStatusRepository = paymentStatusRepository;
        this.paymentTypesRepository = paymentTypesRepository;
        this.repository = repository;
        this.statusRepository = statusRepository;
        this.goodsRepository = goodsRepository;
        this.clientRepository = clientRepository;
        this.usersRepository = usersRepository;
        this.deliveryStatusRepository = deliveryStatusRepository;
        this.deliveryTypeRepository = deliveryTypeRepository;
    }

    @Override
    public String getHome(Model model) {
        model.addAttribute("object", new OrderEntity());
        model.addAttribute("objects", repository.findAllWithSum());
        return pageWithContent("orders/menu", model);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String create(@ModelAttribute OrderEntity object, Model model){
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny();

        model.addAttribute("object", object);
        model.addAttribute("objects", repository.findAll(Example.of(object, exampleMatcher)));

        return pageWithContent("orders/menu", model);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model){
        if (model.getAttribute("orderEntity")==null)
            model.addAttribute(new OrderEntity());

        return pageWithContent(folder + "/edit", model);
    }

    @RequestMapping(value = "/create", params = "save")
    public Object create(@Valid @ModelAttribute OrderEntity object, final BindingResult bindingResult, Model model) throws Exception{
        if (!bindingResult.hasErrors()) {
            repository.save(object);
            return defaultPage(model);
        }else{
            object.setId(null);
            model.addAttribute(object);
            return pageWithContent(folder + "/edit", model);
        }
    }

    @RequestMapping(value = "/create", params = "addGood")
    public Object addGoodSave(@ModelAttribute OrderEntity dto, @RequestParam("addGood")Long goodId, Model model){
        dto.getGoods().add(goodsRepository.getById(goodId));
        return pageWithContent(folder + "/edit", model);
    }

    @RequestMapping(value = "/create", params = "removeGood")
    public Object removeGoodSave(@ModelAttribute OrderEntity dto, @RequestParam("removeGood")Long index, Model model){
        dto.getGoods().remove(index.intValue());
        return pageWithContent(folder + "/edit", model);
    }

    @RequestMapping(value = "/create", params = "search")
    public Object findGoodsSave(@ModelAttribute OrderEntity dto, RedirectAttributes redirectAttributes, @RequestParam("q") String param, Model model){
        model.addAttribute("selectGoods", goodsRepository.findAllByNameContainsIgnoreCase(param));
        model.addAttribute("q", param);

        return redirect(getPageUrl() + "create#search", model, redirectAttributes);
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Long id){
        OrderEntity v = repository.findById(id).orElseThrow();

        model.addAttribute(v);
        return pageWithContent(folder + "/edit", model);
    }

    @RequestMapping(value = "/{id}/edit", params = "save")
    public Object update(@Valid @ModelAttribute OrderEntity dto, final BindingResult bindingResult, @PathVariable Long id,  Model model) throws IllegalAccessException {
        if (!bindingResult.hasErrors()) {
            OrderEntity v = repository.findById(id).orElseThrow();
            for (Field f :
                    v.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                f.set(v, f.get(dto));
            }
            repository.save(v);
            return defaultPage(model);
        }else{
            model.addAttribute(dto);
            return pageWithContent(folder + "/edit", model);
        }
    }

    @RequestMapping(value = "/{id}/edit", params = "remove")
    public Object remove(@PathVariable Long id, Model model){
        OrderEntity v = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        model.addAttribute(v);
        return defaultPage(model);
    }

    @RequestMapping(value = "/{id}/edit", params = "addGood")
    public Object addGood(@Valid @ModelAttribute OrderEntity dto, @RequestParam("addGood")Long goodId, @PathVariable Long id,  Model model){
        dto.getGoods().add(goodsRepository.getById(goodId));
        return pageWithContent(folder + "/edit", model);
    }

    @RequestMapping(value = "/{id}/edit", params = "removeGood")
    public Object removeGood(@Valid @ModelAttribute OrderEntity dto, @RequestParam("removeGood")Long index, @PathVariable Long id, Model model){
        dto.getGoods().remove(index.intValue());
        return pageWithContent(folder + "/edit", model);
    }

    @RequestMapping(value = "/{id}/edit", params = "search")
    public Object findGoods(@Valid @ModelAttribute OrderEntity dto, @RequestParam("q") String param, Model model, @PathVariable String id){
        model.addAttribute("selectGoods", goodsRepository.findAllByNameContainsIgnoreCase(param));
        model.addAttribute("q", param);
        return pageWithContent(folder + "/edit", model);
    }

    @RequestMapping(value = "/{id}/addPayment")
    public Object addPayment(Model model, @PathVariable Long id){
        OrderEntity order = repository.findById(id).orElseThrow();
        PaymentEntity payment = new PaymentEntity(order);
        paymentRepository.save(payment);
        return redirect(getPageUrl() + id + "/edit", model, null);
    }



    @ModelAttribute("clients")
    private List<ClientEntity> getClients(){
        return clientRepository.findAll();
    }

    @ModelAttribute("statuses")
    private List<OrderStatusEntity> getStatuses(){
        return statusRepository.findAll();
    }

    @ModelAttribute("deliveryTypes")
    private List<DeliveryTypeEntity> getDeliveryTypes(){
        return deliveryTypeRepository.findAll();
    }

    @ModelAttribute("deliveryStatuses")
    private List<DeliveryStatusEntity> getDeliveryStatuses(){
        return deliveryStatusRepository.findAll();
    }

    @ModelAttribute("paymentTypes")
    private List<PaymentTypeEntity> getPaymentTypes(){
        return paymentTypesRepository.findAll();
    }

    @ModelAttribute("paymentStatuses")
    private List<PaymentStatusEntity> getPaymentStatuses(){
        return paymentStatusRepository.findAll();
    }

    @ModelAttribute("employees")
    private List<UserEntity> getUsers(){
        return usersRepository.findAll();
    }

    @ModelAttribute("allGoods")
    private List<GoodEntity> getGoods(){
        return goodsRepository.findAll();
    }
}
