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

    @Query(value = "select * from address a where upper(a.region) similar to ?1 or " +
            "upper(a.city) similar to ?1 or " +
            "upper(a.street) similar to ?1", nativeQuery = true)
    List<AddressEntity> findAddresses(String q);

    default List<AddressEntity> parseAndFind(String q){
        String[] arr = q.toUpperCase().split(" ");
        StringBuilder qBuilder = new StringBuilder(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            qBuilder.append("|");
            qBuilder.append(arr[i]);
        }
        return findAddresses("%(" + qBuilder + ")%");
    }

}
