package net.artux.nextcrm.model.address;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "address")
@Entity
public class  AddressEntity extends BaseEntity {

    private Integer index;
    private String region;
    private String city;
    private String street;
    private Integer house;
    private Integer entrance;
    private Integer building;
    private Integer flat;
}
