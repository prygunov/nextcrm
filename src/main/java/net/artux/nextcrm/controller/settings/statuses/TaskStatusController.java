package net.artux.nextcrm.controller.settings.statuses;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.task.TaskStatusEntity;
import net.artux.nextcrm.repository.settings.statuses.TaskStatusRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings/statuses/tasks")
public class TaskStatusController extends BaseRepositoryController<TaskStatusEntity, TaskStatusRepository> {

    public TaskStatusController(TaskStatusRepository repository){
        super("Статусы - Задачи", "settings/statuses", repository);
    }

}
