package net.artux.nextcrm.model.role;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "user_role")
@Entity
public class RoleEntity extends BaseEntity {

    private String name;
    private boolean tasks;
    private boolean clients;
    private boolean orders;
    private boolean analysis;

}
