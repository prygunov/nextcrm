package net.artux.nextcrm.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "role")
@Entity
public class RoleEntity extends BaseEntity{

    private String name;
    private boolean tasks;
    private boolean clients;
    private boolean orders;
    private boolean analysis;

}
