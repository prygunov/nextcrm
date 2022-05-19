package net.artux.nextcrm.repository;

import net.artux.nextcrm.model.appeal.AppealEntity;
import net.artux.nextcrm.model.client.ClientEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface AppealsRepository extends CRepository<AppealEntity> {

    List<AppealEntity> findAllByStatus_EndingIsFalse();

    @Query(value = "with appeals as (select * from appeal a where a.time >= ?1 and a.time <= ?2) " +
            "select c.\"name\", count(a.id), count(as2.ending) as closed, " +
            "(count(a.id) * 100 / coalesce((select count(id) from appeals), 1)) as percent  from channel c  " +
            "left join appeals a on a.channel_id = c.id  " +
            "left join appeal_status as2 on as2.id = a.status_id and as2.ending = true " +
            "group by c.\"name\" ", nativeQuery = true)
    List<AnalyticChannel> getChannelAnalytics(Date start, Date end);

}
