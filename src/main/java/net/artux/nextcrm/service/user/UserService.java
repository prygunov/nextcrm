package net.artux.nextcrm.service.user;

import net.artux.nextcrm.model.user.UserCreateDto;
import net.artux.nextcrm.model.user.UserDto;
import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.model.user.UserMapper;
import net.artux.nextcrm.repository.UsersRepository;
import net.artux.nextcrm.service.AbstractService;
import net.artux.nextcrm.service.CService;
import net.artux.nextcrm.service.role.RoleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService extends AbstractService<UserEntity, UserCreateDto, UserDto, UsersRepository> {
    private final UserMapper mapper;
    private final RoleService roleService;
    private final UsersRepository repository;
    private final PasswordEncoder encoder;
    private final UserValidationService validationService;

    UserService(UsersRepository repository, UserMapper mapper, RoleService roleService, UsersRepository repository1, PasswordEncoder encoder, UserValidationService validationService) {
        super(repository);
        this.mapper = mapper;
        this.roleService = roleService;
        this.repository = repository1;
        this.encoder = encoder;
        this.validationService = validationService;
    }

    public List<UserEntity> getAllUsers() {
        return repository.getAll();
    }

    @Override
    public UserCreateDto create(UserCreateDto dto) {
        if (validationService.isValid(dto)) {
            UserEntity entity = mapper.fromForRegister(dto);
            entity.setPassword(encoder.encode(dto.getPassword()));
            repository.save(entity);
            return dto;
        }
        return null;
    }

    @Override
    public UserCreateDto update(Long id, UserCreateDto dto) {
        UserEntity entity = repository.findById(id).orElseThrow();
        entity.setLogin(dto.getLogin());
        entity.setFirstname(dto.getFirstname());
        entity.setLastname(dto.getLastname());
        entity.setApproved(dto.isApproved());
        if (dto.getRole_id() != null)
            entity.setRole(roleService.getWithId(dto.getRole_id()));
        else
            entity.setRole(null);
        repository.save(entity);
        return dto;
    }

    @Override
    public UserDto read(Long id) {
        return mapper.to(findById(id, "Пользователь не найден"));
    }

    @Override
    public List<UserDto> readAll() {
        return mapper.list(repository.findAll());
    }
}
