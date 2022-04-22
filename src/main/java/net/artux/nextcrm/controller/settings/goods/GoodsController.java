package net.artux.nextcrm.controller.settings.goods;

import net.artux.nextcrm.controller.util.BaseEntityController;
import net.artux.nextcrm.model.order.goods.CategoryEntity;
import net.artux.nextcrm.model.order.goods.GoodDto;
import net.artux.nextcrm.model.order.goods.GoodEntity;
import net.artux.nextcrm.repository.settings.goods.CategoriesRepository;
import net.artux.nextcrm.service.orders.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/settings/goods")
public class GoodsController extends BaseEntityController<GoodDto, GoodDto, GoodEntity, GoodsService> {

    private final CategoriesRepository categoriesRepository;

    public GoodsController(GoodsService service, CategoriesRepository categoriesRepository){
        super("Товары", "settings/goods", service);
        this.categoriesRepository = categoriesRepository;
    }

    @ModelAttribute("categories")
    public List<CategoryEntity> getCategories(){
        return categoriesRepository.findAll();
    }
}
