package net.artux.nextcrm.controller.settings.goods;

import net.artux.nextcrm.controller.BaseEntityController;
import net.artux.nextcrm.model.order.goods.CategoryDto;
import net.artux.nextcrm.model.order.goods.CategoryEntity;
import net.artux.nextcrm.service.orders.CategoriesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/settings/goods/categories")
public class CategoriesController extends BaseEntityController<CategoryDto, CategoryDto, CategoryEntity,
        CategoriesService> {

    public CategoriesController(CategoriesService repository){
        super("Категории товаров", "settings/goods/categories", repository);
    }

    @ModelAttribute("categories")
    public List<CategoryDto> getCategories(){
        return service.readAll();
    }
}
