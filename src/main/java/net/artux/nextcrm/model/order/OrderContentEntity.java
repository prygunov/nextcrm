package net.artux.nextcrm.model.order;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.order.goods.GoodEntity;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Data
@Getter
@RequiredArgsConstructor
@Embeddable
public class OrderContentEntity extends BaseEntity implements Serializable {

    @OneToOne
    private OrderEntity order;
    @OneToOne
    private GoodEntity good;
    private int quantity;
    private float discount;

}
