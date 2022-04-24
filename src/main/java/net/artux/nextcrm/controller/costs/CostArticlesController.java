package net.artux.nextcrm.controller.costs;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.costs.CostArticleEntity;
import net.artux.nextcrm.model.costs.CostEntity;
import net.artux.nextcrm.repository.costs.CostArticleRepository;
import net.artux.nextcrm.repository.costs.CostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/costs/articles")
public class CostArticlesController extends BaseRepositoryController<CostArticleEntity, CostArticleRepository> {

    public CostArticlesController(CostArticleRepository repository) {
        super("Статьи расходов", "costs/articles", repository);
    }


}
