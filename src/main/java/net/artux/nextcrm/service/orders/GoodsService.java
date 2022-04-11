package net.artux.nextcrm.service.orders;

import net.artux.nextcrm.model.order.goods.*;
import net.artux.nextcrm.repository.settings.goods.GoodsRepository;
import net.artux.nextcrm.service.AbstractService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoodsService extends AbstractService<GoodEntity, GoodDto, GoodDto, GoodsRepository> {

   private final GoodMapper mapper;
   private final CategoriesService categoriesService;

   public GoodsService(GoodsRepository repository, GoodMapper mapper, CategoriesService categoriesService) {
      super(repository);
      this.mapper = mapper;
      this.categoriesService = categoriesService;
   }

   @Override
   public GoodDto create(GoodDto dto) {
      GoodEntity entity = mapper.from(dto);
      if (dto.getCategoryId() != null)
         entity.setCategory(categoriesService
                 .findById(dto.getCategoryId(), "Категория не найдена"));
      repository.save(entity);
      return dto;
   }

   @Override
   public GoodDto getForEdit(Long id) {
      return read(id);
   }

   @Override
   public GoodDto update(Long id, GoodDto dto) {
      GoodEntity entity = mapper.from(dto);
      if (dto.getCategoryId()!=null)
         entity.setCategory(categoriesService
                 .findById(dto.getCategoryId(), "Категория не найдена"));

      GoodEntity base = repository.findById(id).orElseThrow();
      if (!base.equals(entity)){
         base.setName(entity.getName());
         base.setCategory(entity.getCategory());
         base.setCode(entity.getCode());
         base.setPhoto(entity.getPhoto());
         base.setPrice(entity.getPrice());
         repository.save(base);
      }
      return dto;
   }

   @Override
   public GoodDto read(Long id) {
      return mapper.to(repository.getById(id));
   }

   @Override
   public List<GoodDto> readAll() {
      return mapper.list(repository.findAll());
   }


}
