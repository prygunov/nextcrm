package net.artux.nextcrm.model.order;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.costs.CostEntity;
import net.artux.nextcrm.model.order.delivery.DeliveryEntity;
import net.artux.nextcrm.model.order.goods.GoodEntity;
import net.artux.nextcrm.model.order.payment.PaymentEntity;
import net.artux.nextcrm.model.user.UserEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "app_order")
@Entity
public class OrderEntity extends BaseEntity {

    @OneToOne
    @NotNull
    private ClientEntity client;

    @OneToOne
    @NotNull
    private OrderStatusEntity status;

    @OneToOne
    private DeliveryEntity delivery;

    @OneToMany
    private List<PaymentEntity> payments;

    @ManyToOne
    @NotNull
    private UserEntity employee;

    @OneToMany
    @Size(min = 1, max = 100)
    private List<GoodEntity> goods;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull
    private Date time = new Date();

    private String comment;

    @OneToMany
    @JoinColumn(name = "order_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<OrderEventEntity> events;

    @OneToMany
    @JoinColumn(name = "order_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<CallEntity> calls;

    @OneToMany
    @JoinColumn(name = "order_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<CostEntity> costs;
}
