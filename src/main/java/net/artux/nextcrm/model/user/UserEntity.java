package net.artux.nextcrm.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.role.RoleEntity;

import javax.persistence.*;
import javax.validation.Constraint;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "app_user")
@Entity
public class UserEntity extends BaseEntity {

    @Column(unique = true)
    private String login;
    private String firstname;
    private String lastname;
    private Boolean approved;
    private String password;
    @ManyToOne
    private RoleEntity role;

}
