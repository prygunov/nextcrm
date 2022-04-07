package net.artux.nextcrm.model.order.delivery;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.order.OrderStatusEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "delivery_status")
@Entity
public class DeliveryStatusEntity extends BaseEntity {

    private String name;
    private boolean ending;

}
