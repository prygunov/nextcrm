package net.artux.nextcrm.controller.settings.statuses;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.appeal.AppealStatusEntity;
import net.artux.nextcrm.repository.settings.statuses.AppealStatusRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings/statuses/appeals")
public class AppealStatusController extends BaseRepositoryController<AppealStatusEntity, AppealStatusRepository> {

    public AppealStatusController(AppealStatusRepository repository){
        super("Статусы - Обращения", "settings/statuses", repository);
    }

}
