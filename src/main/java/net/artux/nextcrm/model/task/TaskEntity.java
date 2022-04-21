package net.artux.nextcrm.model.task;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.appeal.AppealEntity;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.user.UserEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "task")
@Data
@Entity
@RequiredArgsConstructor
@Getter
public class TaskEntity extends BaseEntity {

    @ManyToOne
    private TaskStatusEntity status;
    private String name;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;
    @ManyToOne
    private UserEntity employee;

    @ManyToOne
    private AppealEntity appeal;
    @ManyToOne
    private OrderEntity order;

}
