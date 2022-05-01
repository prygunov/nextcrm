package net.artux.nextcrm.controller.clients;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.address.AddressEntity;
import net.artux.nextcrm.repository.clients.AddressesRepository;
import net.artux.nextcrm.repository.clients.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/addresses")
public class AddressController extends BaseRepositoryController<AddressEntity, AddressesRepository> {

    private final ClientRepository clientRepository;

    public AddressController(AddressesRepository repository, ClientRepository clientRepository){
        super("Адреса", "clients/address", repository);
        this.clientRepository = clientRepository;
    }

    @Override
    public String getHome(Model model) {
        model.addAttribute("objects", repository.getAddresses());
        model.addAttribute("relative", "");
        return pageWithContent(folder + "/view", model);
    }

    @RequestMapping(value = "/{region}", method = RequestMethod.GET)
    public String get(Model model, @PathVariable String region) {
        model.addAttribute("objects", repository.getAddresses(region));
        model.addAttribute("relative", region + '/');
        return pageWithContent(folder + "/view", model);
    }

    @RequestMapping(value = "/{region}/{city}", method = RequestMethod.GET)
    public String get(Model model, @PathVariable String region, @PathVariable String city) {
        model.addAttribute("objects", repository.getAddresses(region, city));
        model.addAttribute("relative", region + '/' + city  + '/');
        return pageWithContent(folder + "/view", model);
    }

    @RequestMapping(value = "/{region}/{city}/{street}", method = RequestMethod.GET)
    public String get(Model model, @PathVariable String region, @PathVariable String city, @PathVariable String street) {
        model.addAttribute("objects", repository.getAddresses(region, city, street));
        model.addAttribute("relative", region + '/' + city  + '/' + street);
        model.addAttribute("final", true);
        return pageWithContent(folder + "/view", model);
    }
}
