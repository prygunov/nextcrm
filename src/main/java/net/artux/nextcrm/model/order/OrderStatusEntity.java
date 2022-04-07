package net.artux.nextcrm.model.order;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.client.ClientEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "order_status")
@Entity
public class OrderStatusEntity extends BaseEntity {

    private String name;
    private boolean ending;
}
