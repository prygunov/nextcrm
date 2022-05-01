package net.artux.nextcrm.repository.clients;

import net.artux.nextcrm.model.address.AddressEntity;
import net.artux.nextcrm.repository.CRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressesRepository extends CRepository<AddressEntity> {

    @Query("select distinct region from AddressEntity a")
    List<String> getAddresses();

    @Query("select distinct city from AddressEntity a where a .region = :region")
    List<String> getAddresses(String region);

    @Query("select distinct street from AddressEntity a where a .region = :region and a .city = :city")
    List<String> getAddresses(String region, String city);

    @Query("select a from AddressEntity a where a.region = :region and a.city = :city and a.street = :street")
    List<AddressEntity> getAddresses(String region, String city, String street);

}
