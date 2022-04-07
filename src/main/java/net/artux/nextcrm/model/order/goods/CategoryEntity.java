package net.artux.nextcrm.model.order.goods;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.order.OrderStatusEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "category")
@Entity
public class CategoryEntity extends BaseEntity {

    @ManyToOne
    private CategoryEntity parent;
    private String name;

}
