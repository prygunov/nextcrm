package net.artux.nextcrm.service.role;

import net.artux.nextcrm.model.role.RoleDto;
import net.artux.nextcrm.model.role.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id", ignore = true)
    RoleEntity from(RoleDto dto);

}
