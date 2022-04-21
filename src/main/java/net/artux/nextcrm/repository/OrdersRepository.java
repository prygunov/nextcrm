package net.artux.nextcrm.repository;

import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.order.OrderEntity;

import java.util.List;

public interface OrdersRepository extends CRepository<OrderEntity> {

    List<OrderEntity> findAllByStatus_EndingIsFalse();

}
