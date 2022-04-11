package net.artux.nextcrm.model.order.goods;

import net.artux.nextcrm.model.user.UserCreateDto;
import net.artux.nextcrm.model.user.UserDto;
import net.artux.nextcrm.model.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CategoryMapper {
    @Mapping(target = "parent", ignore = true)
    CategoryEntity from(CategoryDto dto);

    @Mapping(target = "parentName", expression = "java(entity.getParent() != null ? entity.getParent().getName() : null)")
    @Mapping(target = "parentId", expression = "java(entity.getParent() != null ? entity.getParent().getId() : null)")
    CategoryDto to(CategoryEntity entity);
    List<CategoryDto> list(List<CategoryEntity> entities);
}
