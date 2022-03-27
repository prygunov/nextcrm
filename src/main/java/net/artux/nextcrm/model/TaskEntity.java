package net.artux.nextcrm.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "task")
@Data
@Entity
@RequiredArgsConstructor
@Getter
public class TaskEntity extends BaseEntity {

    private String name;
    private String description;

}
