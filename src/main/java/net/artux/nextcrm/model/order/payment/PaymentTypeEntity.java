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
@Table(name = "payment_type")
@Entity
public class PaymentTypeEntity extends BaseEntity {

    private String name;

}
