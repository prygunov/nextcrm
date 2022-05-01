package net.artux.nextcrm.model.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.user.UserEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table(name = "task_comment")
@Setter
@Entity
@RequiredArgsConstructor
@Getter
public class TaskCommentEntity extends BaseEntity {

    @ManyToOne
    private TaskEntity task;
    @ManyToOne
    @NotNull
    private UserEntity author;

    private String content;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date time;

}
