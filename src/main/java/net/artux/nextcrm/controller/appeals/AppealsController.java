package net.artux.nextcrm.controller.appeals;

import net.artux.nextcrm.controller.BaseRepositoryController;
import net.artux.nextcrm.model.appeal.AppealEntity;
import net.artux.nextcrm.model.appeal.AppealStatusEntity;
import net.artux.nextcrm.repository.AppealsRepository;
import net.artux.nextcrm.repository.PotentialClientRepository;
import net.artux.nextcrm.repository.settings.statuses.AppealStatusRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/appeals")
public class AppealsController extends BaseRepositoryController<AppealEntity, AppealsRepository> {

    private final AppealStatusRepository statusRepository;
    private final PotentialClientRepository potentialClientRepository;

    public AppealsController(AppealsRepository repository, AppealStatusRepository statusRepository, PotentialClientRepository potentialClientRepository){
        super("Обращения", "appeals", repository);
        this.statusRepository = statusRepository;
        this.potentialClientRepository = potentialClientRepository;
    }

    @RequestMapping(value = "/createPotential", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute AppealEntity object, Model model){
        object.setPotentialClient(potentialClientRepository
                .save(object.getPotentialClient()));
        object.setTime(new Date());
        repository.save(object);
        return new ModelAndView("redirect:" + getPageUrl());
    }

    @ModelAttribute("statuses")
    List<AppealStatusEntity> getStatuses(){
        return statusRepository.findAll();
    }

}
