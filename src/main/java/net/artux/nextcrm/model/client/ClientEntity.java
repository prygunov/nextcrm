package net.artux.nextcrm.model.client;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.address.AddressEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "client")
@Entity
public class ClientEntity extends BaseEntity {

    private String name;
    private String lastname;
    private String phone;
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Sex sex;
    private Date registrationDate;
    private String information;

    @ManyToMany
    private List<AddressEntity> addresses;
    @ManyToOne
    private AddressEntity defaultAddress;
}
