package net.artux.nextcrm.repository.settings.management;

import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.repository.CRepository;
import net.artux.nextcrm.repository.orders.AnalyticUser;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CRepository<UserEntity> {

    @Query(value = "select * from app_user au " +
            "left join (" +
            "select  employee_id, avg(stat.s), count(stat.id), (sum(stat.s) - sum(stat.cost)) as profit from (" +
            "select o.id as id, sum(g.price) as s, o.employee_id as employee_id, coalesce (sum(c.sum),0) as cost from app_order o " +
            "join app_order_goods og on o.id = og.order_entity_id " +
            "join good g on g.id = og.goods_id " +
            "left join cost c on c.order_id = o.id " +
            "where o.time >= ?1 and o.time <= ?2 " +
            "group by o.id, o.employee_id) as stat " +
            "group by stat.employee_id) r on r.employee_id = au.id", nativeQuery = true)
    List<AnalyticUser> getUserAnalytics(Date start, Date end);

    Optional<UserEntity> findByLogin(String login);

    List<UserEntity> findAllByApprovedIsTrue();

    @Query(value = "SELECT * from app_user u inner join user_role r on u.role_id = r.id or u.role_id is null", nativeQuery = true)
    List<UserEntity> getAll();

}
