package net.artux.nextcrm.model.order;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.order.goods.GoodEntity;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "app_order_goods")
@Entity
@Embeddable
public class OrderGoodEntity implements Serializable {

    @Id
    private Long order_entity_id;
    @Id
    private Long goods_id;

}
