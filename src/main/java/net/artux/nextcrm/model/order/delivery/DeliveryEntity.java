package net.artux.nextcrm.model.order.delivery;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.address.AddressEntity;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.order.OrderStatusEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "delivery")
@Entity
public class DeliveryEntity extends BaseEntity {

    @OneToOne
    private DeliveryTypeEntity type;

    @ManyToOne
    private DeliveryStatusEntity status;

    @OneToOne
    private OrderEntity order;

    @ManyToOne
    private AddressEntity address;

    private Date date;
    private String information;
}
