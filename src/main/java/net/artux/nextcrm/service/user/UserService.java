package net.artux.nextcrm.service.user;

import net.artux.nextcrm.model.user.UserCreateDto;
import net.artux.nextcrm.model.user.UserDto;
import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.model.user.UserMapper;
import net.artux.nextcrm.repository.settings.management.RoleRepository;
import net.artux.nextcrm.repository.settings.management.UsersRepository;
import net.artux.nextcrm.service.AbstractService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService extends AbstractService<UserEntity, UserCreateDto, UserDto, UsersRepository> {
    private final UserMapper mapper;
    private final RoleRepository roleRepository;
    private final UsersRepository repository;
    private final PasswordEncoder encoder;
    private final UserValidationService validationService;

    UserService(UsersRepository repository, UserMapper mapper, RoleRepository roleRepository, UsersRepository repository1, PasswordEncoder encoder, UserValidationService validationService) {
        super(repository);
        this.mapper = mapper;
        this.roleRepository = roleRepository;
        this.repository = repository1;
        this.encoder = encoder;
        this.validationService = validationService;
    }

    public UserCreateDto register(UserCreateDto dto){
        if (validationService.isValid(dto)) {
            UserEntity entity = mapper.fromForRegister(dto);
            entity.setPassword(encoder.encode(dto.getPassword()));
            repository.save(entity);
            return dto;
        }
        return dto;
    }

    @Override
    public UserCreateDto create(UserCreateDto dto) {
        if (validationService.isValid(dto)) {
            UserEntity entity = mapper.fromForRegister(dto);
            entity.setPassword(encoder.encode(dto.getPassword()));
            entity.setApproved(dto.isApproved());
            if (dto.getRoleId() != null)
                entity.setRole(roleRepository.getById(dto.getRoleId()));
            else
                entity.setRole(null);
            repository.save(entity);
            return dto;
        }
        return dto;
    }

    @Override
    public UserCreateDto getForEdit(Long id) {
        return mapper.dto(findById(id, "Не найден"));
    }

    @Override
    public UserCreateDto update(Long id, UserCreateDto dto) {
        UserEntity entity = repository.findById(id).orElseThrow();
        entity.setLogin(dto.getLogin());
        entity.setFirstname(dto.getFirstname());
        entity.setLastname(dto.getLastname());
        entity.setApproved(dto.isApproved());
        if (dto.getRoleId() != null)
            entity.setRole(roleRepository.getById(dto.getRoleId()));
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
