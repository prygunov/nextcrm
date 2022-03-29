package net.artux.nextcrm.service.role;

import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.role.RoleDto;
import net.artux.nextcrm.model.role.RoleEntity;
import net.artux.nextcrm.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleService {

   private final RoleRepository repository;
   private final RoleMapper roleMapper;

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

   public void createRole(RoleDto dto){
      repository.save(roleMapper.from(dto));
   }
}
