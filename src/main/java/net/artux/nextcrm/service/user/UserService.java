package net.artux.nextcrm.service.user;

import net.artux.nextcrm.model.UserEntity;
import net.artux.nextcrm.model.dto.UserDto;
import net.artux.nextcrm.service.BaseService;
import net.artux.nextcrm.service.RoleService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService extends BaseService<UserEntity> {
    private final UserMapper mapper;
    private final RoleService roleService;

    public UserService(JdbcTemplate template, UserMapper mapper, RoleService roleService) {
        super(template);
        this.mapper = mapper;
        this.roleService = roleService;
    }

    public void registerUser(UserDto userDto){
        repository.save(mapper.from(userDto));
    }
    public int updateUser(Long id, UserDto dto){
        UserEntity entity = mapper.from(dto);
        entity.setRole(roleService.getWithId(dto.getRole_id()));

        return repository.update(id, mapper.from(dto));
    }

    public List<UserEntity> getAllUsers(){
        return repository.findAll();
    }

    public UserEntity getUser(Long id){
        return repository.findById(id).orElseThrow();
    }

}
