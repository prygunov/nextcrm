package net.artux.nextcrm.repository.orders;

import net.artux.nextcrm.model.order.OrderDto;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.repository.CRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends CRepository<OrderEntity> {

    List<OrderEntity> findAllByStatus_EndingIsFalse();
    List<OrderEntity> findAllByEmployeeId(Long id);
    List<OrderEntity> findAllByClientId(Long id);

    @Query(value = "SELECT  NEW net.artux.nextcrm.model.order.OrderDto (ao.id, os.name, au.login, ao.employee.id, ao.time, ao.client.id, concat(c.name, ' ', c.lastname), sum(g.price))\n" +
            "from OrderEntity ao \n" +
            " join OrderGoodEntity aog on ao.id = aog.order_entity_id \n" +
            " join GoodEntity g on g.id = aog.goods_id \n" +
            " join OrderStatusEntity os on os.id = ao.status.id\n" +
            " join UserEntity au on au.id = ao.employee.id \n" +
            " join ClientEntity c on c.id = ao.client.id \n" +
            "group by ao.id, au.id, os.name, au.login, c.name, c.lastname, c.id")
    List<OrderDto> findAllWithSum();

    @Query(value = "SELECT  NEW net.artux.nextcrm.model.order.OrderDto (ao.id, os.name, au.login, ao.employee.id, ao.time, ao.client.id, concat(c.name, ' ', c.lastname), sum(g.price))\n" +
            "from OrderEntity ao \n" +
            " join OrderGoodEntity aog on ao.id = aog.order_entity_id \n" +
            " join GoodEntity g on g.id = aog.goods_id \n" +
            " join OrderStatusEntity os on os.id = ao.status.id\n" +
            " join UserEntity au on au.id = ao.employee.id \n" +
            " join ClientEntity c on c.id = :clientId \n" +
            "group by ao.id, au.id, os.name, au.login, c.name, c.lastname, c.id")
    List<OrderDto> getDtosForClient(@Param("clientId") Long clientId);

    @Query(value = "SELECT  NEW net.artux.nextcrm.model.order.OrderDto (ao.id, os.name, au.login, ao.employee.id, ao.time, ao.client.id, concat(c.name, ' ', c.lastname), sum(g.price))\n" +
            "from OrderEntity ao \n" +
            " join OrderGoodEntity aog on ao.id = aog.order_entity_id \n" +
            " join GoodEntity g on g.id = aog.goods_id \n" +
            " join OrderStatusEntity os on os.id = ao.status.id\n" +
            " join UserEntity au on au.id = :userId \n" +
            " join ClientEntity c on c.id = ao.client.id \n" +
            "group by ao.id, au.id, os.name, au.login, c.name, c.lastname, c.id")
    List<OrderDto> getDtosForUser(@Param("userId") Long userId);


}
