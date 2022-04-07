package net.artux.nextcrm.model.task;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

}
