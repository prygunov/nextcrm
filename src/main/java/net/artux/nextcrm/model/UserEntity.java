package net.artux.nextcrm.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "users")
@Entity
public class UserEntity extends BaseEntity{

    private String login;
    private String firstname;
    private String lastname;
    private Boolean approved;
    private String password;
    @ManyToOne
    private RoleEntity role;

}
