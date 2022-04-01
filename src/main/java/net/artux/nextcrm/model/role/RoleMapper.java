package net.artux.nextcrm.model.role;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleEntity from(RoleDto dto);

    List<RoleDto> list(List<RoleEntity> all);
}
