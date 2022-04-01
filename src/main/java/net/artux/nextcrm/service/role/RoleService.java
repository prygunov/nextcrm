package net.artux.nextcrm.service.role;

import net.artux.nextcrm.model.role.RoleDto;
import net.artux.nextcrm.model.role.RoleEntity;
import net.artux.nextcrm.model.role.RoleMapper;
import net.artux.nextcrm.repository.RoleRepository;
import net.artux.nextcrm.service.AbstractService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleService extends AbstractService<RoleEntity, RoleDto, RoleDto, RoleRepository> {

   private final RoleMapper roleMapper;

   public RoleService(RoleRepository repository, RoleMapper roleMapper) {
      super(repository);
      this.roleMapper = roleMapper;
   }

   public RoleEntity getWithId(Long id){
      return repository.getById(id);
   }

   public List<RoleEntity> getAll(){
      return repository.findAll();
   }

   public void changeRole(Long id, String field){
      RoleEntity entity = repository.getById(id);
      switch (field){
         case "tasks":
            entity.setTasks(!entity.isTasks());
            break;
         case "orders":
            entity.setOrders(!entity.isOrders());
            break;
         case "clients":
            entity.setClients(!entity.isClients());
            break;
      }
      repository.save(entity);
   }

   @Override
   public RoleDto create(RoleDto dto) {
      repository.save(roleMapper.from(dto));
      return dto;
   }

   @Override
   public RoleDto update(Long id, RoleDto dto) {
      return null;
   }

   @Override
   public RoleDto read(Long id) {
      return null;
   }

   @Override
   public List<RoleDto> readAll() {
      return roleMapper.list(repository.findAll());
   }
}
