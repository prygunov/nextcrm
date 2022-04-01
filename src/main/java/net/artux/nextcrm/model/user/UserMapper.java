package net.artux.nextcrm.model.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    UserEntity from(UserCreateDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", expression = "java(null)")
    @Mapping(target = "approved", expression = "java(false)")
    UserEntity fromForRegister(UserCreateDto dto);

    @Mapping(target = "role", expression = "java(entity.getRole()!=null ? entity.getRole().getName() : null)")
    UserDto to(UserEntity entity);
    List<UserDto> list(List<UserEntity> entities);
}
