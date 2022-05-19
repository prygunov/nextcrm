package net.artux.nextcrm.repository.clients;

import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.repository.CRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends CRepository<ClientEntity> {

    @Query(value = "select * from client c " +
            "where upper(c.name) similar to ?1 " +
            "or upper(c.lastname) similar to ?1 " +
            "or upper(c.middle_name) similar to ?1", nativeQuery = true)
    List<ClientEntity> filter(String q);

}
