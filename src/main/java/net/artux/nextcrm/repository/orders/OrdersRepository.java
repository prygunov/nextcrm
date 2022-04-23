package net.artux.nextcrm.repository.orders;

import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.order.OrderEventEntity;
import net.artux.nextcrm.repository.CRepository;

import java.util.List;

public interface OrdersRepository extends CRepository<OrderEntity> {

    List<OrderEntity> findAllByStatus_EndingIsFalse();
    List<OrderEntity> findAllByEmployeeId(Long id);
    List<OrderEntity> findAllByClientId(Long id);

}
