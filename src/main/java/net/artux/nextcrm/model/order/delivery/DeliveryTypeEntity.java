package net.artux.nextcrm.model.order.delivery;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "delivery_type")
@Entity
public class DeliveryTypeEntity extends BaseEntity {

    private String name;
    private float price;

}
