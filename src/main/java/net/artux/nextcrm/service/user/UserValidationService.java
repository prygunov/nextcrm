package net.artux.nextcrm.service.user;

import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.user.UserCreateDto;
import net.artux.nextcrm.repository.UsersRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidationService {

    private final UsersRepository repository;

    public boolean isValid(UserCreateDto dto){
        var entity = repository.findByLogin(dto.getLogin());
        if (entity.isPresent())
            throw new RuntimeException("Пользователь с таким логином уже существует");

        if (!dto.getPassword().equals(dto.getMatchingPassword()))
            throw new RuntimeException("Пароли не совпадают");

        return true;
    }

}
