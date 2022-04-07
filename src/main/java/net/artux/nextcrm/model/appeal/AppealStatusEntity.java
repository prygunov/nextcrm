package net.artux.nextcrm.model.appeal;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "appeal_status")
@Entity
public class AppealStatusEntity extends BaseEntity {

    private String name;
    private boolean ending;

}
