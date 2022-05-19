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
            "join task_status s on t.status_id = s.id and (?1 = -1 or s.id = ?1)" +
            "join app_user u on t.employee_id = u.id and (?2 = -1 or u.id = ?2)" +
            "left join app_order o on t.order_id = o.id " +
            "left join appeal a on t.appeal_id = a.id " +
            "where ((?3 != -1 and t.order_id = ?3) or (?3 = -1 and t.order_id is null)) " +
            "and ((?4 != -1 and t.appeal_id = ?4) or (?4 = -1 and t.appeal_id is null))", nativeQuery = true)
    List<TaskEntity> filter(Integer statusId, Integer employeeId, Integer orderId, Integer appealId);

    @Query(value = "select * from task t " +
            "join app_user u on t.employee_id = u.id " +
            "left join task_status s on t.status_id = s.id " +
            "left join app_order o on t.order_id = o.id " +
            "left join appeal a on t.appeal_id = a.id " +
            "where t.employee_id = ?1", nativeQuery = true)
    List<TaskEntity> getForEmployee(Integer employeeId);
}
