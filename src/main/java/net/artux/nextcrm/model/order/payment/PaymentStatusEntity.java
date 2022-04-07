package net.artux.nextcrm.model.order.payment;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "payment_status")
@Entity
public class PaymentStatusEntity extends BaseEntity {

    private String name;
    private boolean ending;

}
