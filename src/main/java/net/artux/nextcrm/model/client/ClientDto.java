package net.artux.nextcrm.model.client;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.CDto;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Getter
public class ClientDto extends CDto {

    private String name;
    private int age;
}
