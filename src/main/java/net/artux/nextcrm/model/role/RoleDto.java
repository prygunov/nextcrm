package net.artux.nextcrm.model.role;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.CDto;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Getter
public class RoleDto extends CDto {

    private String name;
    private boolean tasks;
    private boolean clients;
    private boolean orders;
    private boolean analysis;

}
