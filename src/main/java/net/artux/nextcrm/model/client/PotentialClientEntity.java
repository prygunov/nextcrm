package net.artux.nextcrm.model.client;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.address.AddressEntity;
import net.artux.nextcrm.model.appeal.ChannelEntity;
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
@Table(name = "potential_client")
@Entity
public class PotentialClientEntity extends BaseEntity {

    private String name;
    private String phone;
    private String email;
    private boolean active;
    @ManyToOne
    private ChannelEntity channel;

}
