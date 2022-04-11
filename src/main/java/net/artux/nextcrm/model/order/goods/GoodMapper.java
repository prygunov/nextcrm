package net.artux.nextcrm.model.order.goods;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface GoodMapper {
    @Mapping(target = "category", ignore = true)
    GoodEntity from(GoodDto dto);

    @Mapping(target = "categoryName", expression = "java(entity.getCategory() != null ? entity.getCategory().getName() : null)")
    @Mapping(target = "categoryId", expression = "java(entity.getCategory() != null ? entity.getCategory().getId() : null)")
    GoodDto to(GoodEntity entity);
    List<GoodDto> list(List<GoodEntity> entities);
}
