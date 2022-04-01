package net.artux.nextcrm.model.client;

import net.artux.nextcrm.model.role.RoleDto;
import net.artux.nextcrm.model.role.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    ClientEntity from(ClientDto dto);
    List<ClientDto> listDto(List<ClientEntity> entities);
    ClientDto dto(ClientEntity entity);

}
