package net.artux.nextcrm.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.appeal.AppealStatusEntity;
import net.artux.nextcrm.model.appeal.ChannelEntity;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.client.PotentialClientEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@Table(name = "company_info")
@Entity
public class CompanyInfoEntity extends BaseEntity {

    private String name;
    @Pattern(regexp = "[0-9]+")
    private String inn;
    @Pattern(regexp = "[0-9]+")
    private String paymentAccount;
    @Pattern(regexp = "[0-9]+")
    private String correspondentAccount;
    private String email;
    private String phones;
    private String site;
    private String address;

}
