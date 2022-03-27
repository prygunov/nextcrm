package net.artux.nextcrm.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "client")
@Entity
public class ClientEntity extends BaseEntity {

    private String name;
    private int age;
}
