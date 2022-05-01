package net.artux.nextcrm.model.order.delivery;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.address.AddressEntity;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.order.OrderStatusEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "delivery")
@Entity
public class DeliveryEntity extends BaseEntity {

    @NotNull
    @OneToOne
    private DeliveryTypeEntity type;

    @NotNull
    @ManyToOne
    private DeliveryStatusEntity status;

    @OneToOne
    @NotNull
    private OrderEntity order;

    @NotNull
    @ManyToOne
    private AddressEntity address;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date = new Date();

    private String information;
}
