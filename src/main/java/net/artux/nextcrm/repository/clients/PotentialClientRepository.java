package net.artux.nextcrm.repository.clients;

import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.client.PotentialClientEntity;
import net.artux.nextcrm.repository.CRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PotentialClientRepository extends CRepository<PotentialClientEntity> {

    @Query(value = "select * from potential_client p left join channel c on p.channel_id = c.id " +
            "where p.name like ?1 and ((?2 != -1 and p.channel_id = ?2) or (?2 = -1 and p.channel_id is null)) and p.active = ?3", nativeQuery = true)
    List<PotentialClientEntity> filter(String query, Integer channelId, boolean active);

}
