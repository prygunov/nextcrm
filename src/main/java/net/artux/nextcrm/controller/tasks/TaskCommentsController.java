package net.artux.nextcrm.controller.tasks;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.task.TaskCommentEntity;
import net.artux.nextcrm.model.task.TaskEntity;
import net.artux.nextcrm.repository.tasks.TaskCommentRepository;
import net.artux.nextcrm.repository.tasks.TasksRepository;
import net.artux.nextcrm.repository.settings.management.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/tasks/comments")
public class TaskCommentsController extends BaseRepositoryController<TaskCommentEntity, TaskCommentRepository>{

    private final TasksRepository tasksRepository;
    private final UsersRepository usersRepository;

    public TaskCommentsController(TaskCommentRepository repository, TasksRepository tasksRepository, UsersRepository usersRepository) {
        super("Комментарии", "tasks/comments", repository);

        this.tasksRepository = tasksRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/auto/create")
    public ModelAndView autoCreate(@RequestParam("taskId") Long taskId){
        TaskEntity task = tasksRepository.findById(taskId).get();
        TaskCommentEntity commentEntity = new TaskCommentEntity();
        commentEntity.setAuthor(usersRepository.findByLogin(SecurityContextHolder
                .getContext().getAuthentication().getName()).get());
        commentEntity.setTime(new Date());
        commentEntity.setTask(task);
        commentEntity.setContent("Пустой комментарий");
        commentEntity = repository.save(commentEntity);

        task.getComments().add(commentEntity);
        tasksRepository.save(task);

        return new ModelAndView("redirect:" + "/tasks/"+taskId+"/edit");
    }

    @Override
    protected Object defaultPage(Model model) {
        TaskCommentEntity commentEntity = (TaskCommentEntity) model.getAttribute("taskCommentEntity");
        return new ModelAndView("redirect:" + "/tasks/"+commentEntity.getTask().getId()+"/edit");
    }
}
