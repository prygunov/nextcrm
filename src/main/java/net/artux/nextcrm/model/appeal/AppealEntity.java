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
@Table(name = "appeal")
@Entity
public class AppealEntity extends BaseEntity {

    @ManyToOne
    private AppealStatusEntity status;
    @ManyToOne
    private ChannelEntity channel;
    private String subject;
    private String content;
    private Date time;
    @ManyToOne
    private ClientEntity client;
    @ManyToOne
    private PotentialClientEntity potentialClient;

}
