package net.artux.nextcrm.controller.costs;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.costs.CostArticleEntity;
import net.artux.nextcrm.model.costs.CostEntity;
import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.repository.costs.CostArticleRepository;
import net.artux.nextcrm.repository.costs.CostRepository;
import net.artux.nextcrm.repository.settings.management.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/costs")
public class CostsController extends BaseRepositoryController<CostEntity, CostRepository> {

    private final CostArticleRepository articleRepository;
    private final UsersRepository usersRepository;

    public CostsController(CostRepository repository, CostArticleRepository articleRepository, UsersRepository usersRepository) {
        super("Расходы", "costs", repository);
        this.articleRepository = articleRepository;
        this.usersRepository = usersRepository;
    }

    @ModelAttribute("employees")
    private List<UserEntity> getUsers(){
        return usersRepository.findAll();
    }

    @ModelAttribute("articles")
    private List<CostArticleEntity> getArticles(){
        return articleRepository.findAll();
    }



}
