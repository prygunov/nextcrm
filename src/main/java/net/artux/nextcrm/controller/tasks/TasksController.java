package net.artux.nextcrm.controller.tasks;

import net.artux.nextcrm.controller.util.BaseRepositoryController;
import net.artux.nextcrm.model.appeal.AppealEntity;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.task.TaskEntity;
import net.artux.nextcrm.model.task.TaskStatusEntity;
import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.repository.AppealsRepository;
import net.artux.nextcrm.repository.orders.OrdersRepository;
import net.artux.nextcrm.repository.settings.management.UsersRepository;
import net.artux.nextcrm.repository.settings.statuses.TaskStatusRepository;
import net.artux.nextcrm.repository.tasks.TasksRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TasksController extends BaseRepositoryController<TaskEntity, TasksRepository> {

    private final TaskStatusRepository statusRepository;
    private final UsersRepository usersRepository;
    private final OrdersRepository ordersRepository;
    private final AppealsRepository appealsRepository;

    public TasksController(TasksRepository repository, TaskStatusRepository statusRepository, UsersRepository usersRepository, OrdersRepository ordersRepository, AppealsRepository appealsRepository){
        super("Задачи", "tasks", repository);
        this.statusRepository = statusRepository;
        this.usersRepository = usersRepository;
        this.ordersRepository = ordersRepository;
        this.appealsRepository = appealsRepository;
    }

    @Override
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute TaskEntity object, BindingResult bindingResult, Model model) {
        object.setTime(new Date());
        repository.save(object);
        return new ModelAndView("redirect:" + getPageUrl());
    }

    @RequestMapping(value = "/filter")
    public Object filter(@RequestParam(value = "order", defaultValue = "-1", required = false) Integer orderId,
                               @RequestParam(value = "employee", required = false, defaultValue = "-1") Integer employeeId,
                               @RequestParam(value = "appeal", required = false, defaultValue = "-1") Integer appealId,
                               @RequestParam(value = "status", required = false, defaultValue = "-1") Integer statusId,
                               Model model) {

        model.addAttribute("objects", repository.filter(statusId, employeeId, orderId, appealId));
        model.addAttribute("statusId", statusId);
        model.addAttribute("orderId", orderId);
        model.addAttribute("appealId", appealId);
        model.addAttribute("employeeId", employeeId);

        return getHome(model);
    }

    @ModelAttribute("statuses")
    List<TaskStatusEntity> getStatuses(){
        return statusRepository.findAll();
    }

    @ModelAttribute("employees")
    List<UserEntity> getUsers(){
        return usersRepository.findAllByApprovedIsTrue();
    }

    @ModelAttribute("orders")
    List<OrderEntity> getOrders(){
        return ordersRepository.findAllByStatus_EndingIsFalse();
    }

    @ModelAttribute("appeals")
    List<AppealEntity> getAppeals(){
        return appealsRepository.findAllByStatus_EndingIsFalse();
    }
}
