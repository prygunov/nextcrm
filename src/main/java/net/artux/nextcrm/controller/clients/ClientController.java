package net.artux.nextcrm.controller.clients;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.address.AddressEntity;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.repository.clients.AddressesRepository;
import net.artux.nextcrm.repository.clients.ClientRepository;
import net.artux.nextcrm.repository.orders.OrdersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.function.Predicate;

@Controller
@RequestMapping("/clients")
public class ClientController extends BaseRepositoryController<ClientEntity, ClientRepository> {

    private final OrdersRepository ordersRepository;
    private final AddressesRepository addressesRepository;

    public ClientController(ClientRepository repository, OrdersRepository ordersRepository, AddressesRepository addressesRepository) {
        super("Клиенты", "clients", repository);
        this.ordersRepository = ordersRepository;
        this.addressesRepository = addressesRepository;
    }

    @Override
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Long id) {
        model.addAttribute("orders", ordersRepository.getDtosForClient(id));
        return super.edit(model, id);
    }

    @RequestMapping(value = "/{id}/findAddress", method = RequestMethod.POST)
    public Object findAddresses(Model model, RedirectAttributes redirectAttributes, @PathVariable Long id, @RequestParam("q") String query) {
        model.addAttribute("addresses", addressesRepository.findAddresses('%' + query + '%'));
        model.addAttribute("q", query);

        return redirect(getPageUrl() + id + "/edit#searchAddress", model, redirectAttributes);
    }

    @RequestMapping(value = "/{id}/setAddress", method = RequestMethod.POST)
    public Object addAddress(Model model, @PathVariable Long id, @RequestParam("addressId") Long addressId) {
        ClientEntity clientEntity = repository.getById(id);
        AddressEntity addressEntity = addressesRepository.getById(addressId);
        if (!clientEntity.getAddresses().contains(addressEntity)) {
            clientEntity.getAddresses().add(addressEntity);
            repository.save(clientEntity);
        }

        return redirect(getPageUrl() + id + "/edit", model, null);
    }

    @RequestMapping(value = "/{id}/removeAddress", method = RequestMethod.POST)
    public Object removeAddress(Model model, @PathVariable Long id, @RequestParam("removeId") Long addressId) {
        ClientEntity clientEntity = repository.getById(id);
        clientEntity.getAddresses().removeIf(new Predicate<AddressEntity>() {
            @Override
            public boolean test(AddressEntity addressEntity) {
                return addressEntity.getId().longValue() == addressId;
            }
        });
        repository.save(clientEntity);

        return redirect(getPageUrl() + id + "/edit", model, null);
    }

    @RequestMapping(value = "/{id}/setDefaultAddress", method = RequestMethod.POST)
    public Object setAddress(Model model, @PathVariable Long id, @RequestParam("defaultId") Long addressId) {
        ClientEntity clientEntity = repository.findById(id).orElseThrow();
        AddressEntity addressEntity = addressesRepository.getById(addressId);
        clientEntity.setDefaultAddress(addressEntity);
        repository.save(clientEntity);

        return redirect(getPageUrl() + id + "/edit", model, null);
    }
}