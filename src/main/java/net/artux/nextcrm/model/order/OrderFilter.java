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
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Getter
@RequiredArgsConstructor
public class OrderFilter extends BaseEntity {

    @OneToOne
    private ClientEntity client;
    @OneToOne
    private OrderStatusEntity status;

    @OneToOne
    private DeliveryEntity delivery;

    @ManyToOne
    private UserEntity employee;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
