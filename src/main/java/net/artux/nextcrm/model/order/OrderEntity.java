package net.artux.nextcrm.model.order;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.order.delivery.DeliveryEntity;
import net.artux.nextcrm.model.order.goods.GoodEntity;
import net.artux.nextcrm.model.order.payment.PaymentEntity;
import net.artux.nextcrm.model.user.UserEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "app_order")
@Entity
public class OrderEntity extends BaseEntity {

    @OneToOne
    private ClientEntity client;
    @OneToOne
    private OrderStatusEntity status;

    @OneToOne
    private DeliveryEntity delivery;
    @OneToMany
    private List<PaymentEntity> payments;

    @ManyToOne
    private UserEntity employee;

    @ManyToMany
    private List<GoodEntity> goodEntityList;
    private Date date;
    private String comment;
}
