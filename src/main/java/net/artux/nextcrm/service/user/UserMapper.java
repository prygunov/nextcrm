package net.artux.nextcrm.service.user;

import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.model.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    UserEntity from(UserDto dto);

}
