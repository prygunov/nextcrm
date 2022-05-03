package net.artux.nextcrm.controller.util;

import net.artux.nextcrm.model.CompanyInfoEntity;
import net.artux.nextcrm.repository.CompanyInfoRepository;
import net.artux.nextcrm.repository.orders.OrdersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/settings/company")
public class CompanyInfoController extends BaseController {

    private final CompanyInfoRepository companyInfoRepository;

    public CompanyInfoController(CompanyInfoRepository companyInfoRepository) {
        super("Общие настройки");
        this.companyInfoRepository = companyInfoRepository;
    }

    @Override
    protected Object getHome(Model model) {
        Optional<CompanyInfoEntity> info = companyInfoRepository.findFirst();
        if (model.getAttribute("companyInfoEntity") == null)
            if (info.isPresent())
                model.addAttribute(info.get());
            else
                model.addAttribute(new CompanyInfoEntity());

        return pageWithContent("settings/company", model);
    }

    @PostMapping("/save")
    public Object save(@Valid @ModelAttribute CompanyInfoEntity entity, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            companyInfoRepository.save(entity);
            return redirect("/settings", model, null);
        } else {
            return getHome(model);
        }

    }
}
