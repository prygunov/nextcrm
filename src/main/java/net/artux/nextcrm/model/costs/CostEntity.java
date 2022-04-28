package net.artux.nextcrm.model.costs;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.order.CallEntity;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.order.OrderEventEntity;
import net.artux.nextcrm.model.order.OrderStatusEntity;
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
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "cost")
@Entity
public class CostEntity extends BaseEntity {

    @ManyToOne
    private CostArticleEntity article;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startPeriod;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endPeriod;

    private String comment;
    @ManyToOne
    private UserEntity employee;

    private double sum;

    @ManyToOne
    private OrderEntity order;
}
