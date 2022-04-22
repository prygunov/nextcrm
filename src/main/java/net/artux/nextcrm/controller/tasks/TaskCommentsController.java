package net.artux.nextcrm.controller.tasks;

import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.appeal.AppealEntity;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.task.TaskCommentEntity;
import net.artux.nextcrm.model.task.TaskEntity;
import net.artux.nextcrm.model.task.TaskStatusEntity;
import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.repository.AppealsRepository;
import net.artux.nextcrm.repository.OrdersRepository;
import net.artux.nextcrm.repository.TaskCommentRepository;
import net.artux.nextcrm.repository.TasksRepository;
import net.artux.nextcrm.repository.settings.management.UsersRepository;
import net.artux.nextcrm.repository.settings.statuses.TaskStatusRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tasks/comments")
@RequiredArgsConstructor
public class TaskCommentsController {

    private final TaskCommentRepository repository;
    private final TasksRepository tasksRepository;
    private final UsersRepository usersRepository;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("taskId") Long taskId){
        TaskCommentEntity commentEntity = new TaskCommentEntity();
        commentEntity.setAuthor(usersRepository.findByLogin(SecurityContextHolder
                .getContext().getAuthentication().getName()).get());
        commentEntity.setTime(new Date());
        commentEntity = repository.save(commentEntity);

        TaskEntity task = tasksRepository.findById(taskId).get();
        task.getComments().add(commentEntity);
        tasksRepository.save(task);

        return new ModelAndView("redirect:" + "/tasks/"+taskId+"/edit");
    }

    @GetMapping("/create")
    public ModelAndView edit(@RequestParam("taskId") Long taskId){
        TaskCommentEntity commentEntity = new TaskCommentEntity();
        commentEntity.setAuthor(usersRepository.findByLogin(SecurityContextHolder
                .getContext().getAuthentication().getName()).get());
        commentEntity.setTime(new Date());
        commentEntity = repository.save(commentEntity);

        TaskEntity task = tasksRepository.findById(taskId).get();
        task.getComments().add(commentEntity);
        tasksRepository.save(task);

        return new ModelAndView("redirect:" + "/tasks/"+taskId+"/edit");
    }
}
