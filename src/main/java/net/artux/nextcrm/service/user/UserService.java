package net.artux.nextcrm.service.user;

import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.model.user.UserDto;
import net.artux.nextcrm.repository.UsersRepository;
import net.artux.nextcrm.service.role.RoleService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final RoleService roleService;
    private final UsersRepository repository;

    public void registerUser(UserDto userDto){
        repository.save(mapper.from(userDto));
    }
    public UserEntity updateUser(Long id, UserDto dto){
        UserEntity entity = repository.findById(id).orElseThrow();
        entity.setLogin(dto.getLogin());
        entity.setFirstname(dto.getFirstname());
        entity.setLastname(dto.getLastname());
        entity.setApproved(dto.isApproved());
        entity.setRole(roleService.getWithId(dto.getRole_id()));

        return repository.save(entity);
    }

    public List<UserEntity> getAllUsers(){
        return repository.getAll();
    }

    public UserEntity getUser(Long id){
        return repository.findById(id).orElseThrow();
    }

}
