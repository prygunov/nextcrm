package net.artux.nextcrm.model.task;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "task_status")
@Entity
public class TaskStatusEntity extends BaseEntity {

    private String name;
    private boolean ending;

}
