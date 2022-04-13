package net.artux.nextcrm.service.orders;

import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.order.goods.GoodDto;
import net.artux.nextcrm.model.order.goods.GoodEntity;
import net.artux.nextcrm.model.order.goods.GoodMapper;
import net.artux.nextcrm.repository.OrdersRepository;
import net.artux.nextcrm.repository.settings.goods.GoodsRepository;
import net.artux.nextcrm.service.AbstractService;
import net.artux.nextcrm.service.CService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdersService implements CService<OrderEntity, OrderEntity, OrderEntity> {

   private final OrdersRepository repository;

   public OrdersService(OrdersRepository repository) {
      this.repository = repository;
   }

   @Override
   public OrderEntity create(OrderEntity dto) {
      return null;
   }

   @Override
   public OrderEntity getForEdit(Long id) {
      return null;
   }

   @Override
   public OrderEntity update(Long id, OrderEntity dto) {
      return null;
   }

   @Override
   public OrderEntity read(Long id) {
      return null;
   }

   @Override
   public void delete(Long id) {

   }

   @Override
   public List<OrderEntity> readAll() {
      return null;
   }

  /* @Override
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
   }*/


}
