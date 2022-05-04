package net.artux.nextcrm.repository.tasks;

import net.artux.nextcrm.model.appeal.AppealEntity;
import net.artux.nextcrm.model.task.TaskEntity;
import net.artux.nextcrm.repository.CRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TasksRepository extends CRepository<TaskEntity> {

    @Override
    @Query(value = "select * from task", nativeQuery = true)
    List<TaskEntity> findAll();

    // -1 значит что значение не было задано
    @Query(value = "select * from task t " +
            "inner join task_status s on t.status_id = s.id and (?1 = -1 or s.id = ?1)" +
            "inner join app_user u on t.employee_id = u.id and (?2 = -1 or u.id = ?2)" +
            "inner join app_order o on (?3 = -1 and t.order_id is null) or (t.order_id = o.id and o.id = ?3)", nativeQuery = true)
    List<TaskEntity> filter(Integer statusId, Integer employeeId, Integer orderId, Integer appealId);
}
