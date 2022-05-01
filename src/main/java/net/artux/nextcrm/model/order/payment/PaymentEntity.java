package net.artux.nextcrm.model.order.payment;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.order.OrderEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "payment")
@Entity
public class PaymentEntity extends BaseEntity {

    public PaymentEntity(OrderEntity order) {
        this.order = order;
    }

    @ManyToOne
    private PaymentStatusEntity status;

    @ManyToOne
    private PaymentTypeEntity type;

    @ManyToOne
    private OrderEntity order;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date time;
    private float sum;

}
