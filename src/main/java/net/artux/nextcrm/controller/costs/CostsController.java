package net.artux.nextcrm.controller.costs;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.costs.CostArticleEntity;
import net.artux.nextcrm.model.costs.CostEntity;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.repository.costs.CostArticleRepository;
import net.artux.nextcrm.repository.costs.CostRepository;
import net.artux.nextcrm.repository.orders.OrdersRepository;
import net.artux.nextcrm.repository.settings.management.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/costs")
public class CostsController extends BaseRepositoryController<CostEntity, CostRepository> {

    private final CostArticleRepository articleRepository;
    private final UsersRepository usersRepository;
    private final OrdersRepository ordersRepository;

    public CostsController(CostRepository repository, CostArticleRepository articleRepository, UsersRepository usersRepository, OrdersRepository ordersRepository) {
        super("Расходы", "costs", repository);
        this.articleRepository = articleRepository;
        this.usersRepository = usersRepository;
        this.ordersRepository = ordersRepository;
    }

    @RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
    public String create(@PathVariable("id") Long id, Model model) {
        CostEntity costEntity = new CostEntity();
        costEntity.setOrder(ordersRepository.getById(id));
        model.addAttribute("object", costEntity);
        return pageWithContent(folder + "/edit", model);
    }

    @ModelAttribute("employees")
    private List<UserEntity> getUsers() {
        return usersRepository.findAll();
    }

    @ModelAttribute("articles")
    private List<CostArticleEntity> getArticles() {
        return articleRepository.findAll();
    }

    @ModelAttribute("orders")
    private List<OrderEntity> getOrders() {
        return ordersRepository.findAll();
    }


}
