package net.artux.nextcrm.model.order.payment;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.order.OrderEntity;

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

    @ManyToOne
    private PaymentStatusEntity status;

    @ManyToOne
    private PaymentTypeEntity type;

    private Date time;
    private float sum;

}
