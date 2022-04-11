package net.artux.nextcrm.service.orders;

import net.artux.nextcrm.model.order.goods.CategoryDto;
import net.artux.nextcrm.model.order.goods.CategoryEntity;
import net.artux.nextcrm.model.order.goods.CategoryMapper;
import net.artux.nextcrm.repository.settings.goods.CategoriesRepository;
import net.artux.nextcrm.service.AbstractService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriesService extends AbstractService<CategoryEntity, CategoryDto, CategoryDto, CategoriesRepository> {

   private final CategoryMapper mapper;

   public CategoriesService(CategoriesRepository repository, CategoryMapper mapper) {
      super(repository);
      this.mapper = mapper;
   }

   @Override
   public CategoryDto create(CategoryDto dto) {
      CategoryEntity entity = mapper.from(dto);
      if (dto.getParentId()!=null)
         entity.setParent(repository.getById(dto.getParentId()));
      repository.save(entity);
      return dto;
   }

   @Override
   public CategoryDto getForEdit(Long id) {
      return read(id);
   }

   @Override
   public CategoryDto update(Long id, CategoryDto dto) {
      CategoryEntity entity = repository.findById(id).orElseThrow();
      entity.setName(dto.getName());
      entity.setDescription(dto.getDescription());
      if (dto.getParentId()!=null)
         entity.setParent(repository.getById(dto.getParentId()));
      repository.save(entity);
      return dto;
   }

   @Override
   public CategoryDto read(Long id) {
      return mapper.to(repository.getById(id));
   }

   @Override
   public List<CategoryDto> readAll() {
      return mapper.list(repository.findAll());
   }


}
