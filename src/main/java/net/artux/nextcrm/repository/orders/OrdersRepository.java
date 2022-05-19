package net.artux.nextcrm.repository.orders;

import net.artux.nextcrm.model.Analytics;
import net.artux.nextcrm.model.order.OrderProjection;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.repository.CRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrdersRepository extends CRepository<OrderEntity> {

    @Query(value = "select * from app_order ao " +
            "join order_status os on ao.status_id = os.id and os.ending = false", nativeQuery = true)
    List<OrderEntity> findAllByStatus_EndingIsFalse();

    @Query(value = "select avg(stat.s), count(stat.id) from (" +
            "select sum(g.price) as s, o.id as id from app_order o " +
            "join app_order_goods og on o.id = og.order_entity_id " +
            "join good g on g.id = og.goods_id " +
            "where o.time >= ?1 and o.time <= ?2 " +
            "group by o.id) as stat", nativeQuery = true)
    AverageAndCount getAverage(Date start, Date end);

    @Query(value = "SELECT new net.artux.nextcrm.model.Analytics(sum(g.price), (sum(g.price) - sum(c.sum)), sum(c.sum)) " +
            "from OrderEntity o " +
            "join OrderGoodEntity og on o.id = og.order_entity_id " +
            "join GoodEntity g on g.id = og.goods_id " +
            "left join CostEntity c on c.order.id = o.id " +
            "where o.time >= ?1 and o.time <= ?2")
    Analytics getAnalytics(Date start, Date end);

    @Query(value = "SELECT  ao.id, os.name as statusName, concat(au.firstname, ' ', au.lastname) as employeeName, ao.time," +
            " concat(c.name, ' ', c.lastname) as clientName, sum(g.price)" +
            "from app_order ao \n" +
            " join app_order_goods aog on ao.id = aog.order_entity_id \n" +
            " join good g on g.id = aog.goods_id \n" +
            " join order_status os on os.id = ao.status_id\n" +
            " join app_user au on au.id = ao.employee_id \n" +
            " join client c on c.id = ao.client_id \n" +
            " where (?1 = -1 or ao.employee_id = ?1) and (?2 = -1 or ao.status_id = ?2) and (upper(c.name) similar to ?3 " +
            "or upper(c.lastname) similar to ?3 or upper(c.middle_name) similar to ?3) " +
            "group by ao.id, au.firstname, au.lastname, os.name, c.name, c.lastname", nativeQuery = true)
    List<OrderProjection> filter(Integer employeeId, Integer statusId, String clientQuery);

    @Query(value = "SELECT  ao.id, os.name as statusName, concat(au.firstname, ' ', au.lastname) as employeeName, ao.time," +
            " concat(c.name, ' ', c.lastname) as clientName, sum(g.price) " +
            "from app_order ao \n" +
            " join app_order_goods aog on ao.id = aog.order_entity_id \n" +
            " join good g on g.id = aog.goods_id \n" +
            " join order_status os on os.id = ao.status_id\n" +
            " join app_user au on au.id = ao.employee_id \n" +
            " join client c on c.id = ao.client_id \n" +
            "group by ao.id, au.id, os.name, au.id, c.name, c.lastname, c.id", nativeQuery = true)
    List<OrderProjection> findAllWithSum();

    @Query(value = "SELECT  ao.id, os.name as statusName, concat(au.firstname, ' ', au.lastname) as employeeName, ao.time," +
            " concat(c.name, ' ', c.lastname) as clientName, sum(g.price)" +
            " from app_order ao" +
            " join app_order_goods aog on ao.id = aog.order_entity_id " +
            " join good g on g.id = aog.goods_id " +
            " join order_status os on os.id = ao.status_id" +
            " join app_user au on au.id = ao.employee_id " +
            " join client c on ao.client_id = c.id " +
            "where c.id = :clientId " +
            "group by ao.id, os.name, au.id, c.name, c.lastname, c.id", nativeQuery = true)
    List<OrderProjection> getForClient(@Param("clientId") Long clientId);

    @Query(value ="SELECT  ao.id, os.name as statusName, concat(au.firstname, ' ', au.lastname) as employeeName, ao.time," +
            " concat(c.name, ' ', c.lastname) as clientName, sum(g.price)" +
            " from app_order ao" +
            " join app_order_goods aog on ao.id = aog.order_entity_id " +
            " join good g on g.id = aog.goods_id " +
            " join order_status os on os.id = ao.status_id" +
            " join app_user au on au.id = ao.employee_id " +
            " join client c on ao.client_id = c.id " +
            "where au.id = :userId " +
            "group by ao.id, au.id, os.name, au.id, c.name, c.lastname, c.id", nativeQuery = true)
    List<OrderProjection> getForUser(@Param("userId") Long userId);


}
