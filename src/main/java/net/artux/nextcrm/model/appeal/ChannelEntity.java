package net.artux.nextcrm.model.appeal;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.client.PotentialClientEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "channel")
@Entity
public class ChannelEntity extends BaseEntity {

    private String name;
    private String description;

}
