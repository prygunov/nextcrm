package net.artux.nextcrm.repository.settings.management;

import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.repository.CRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CRepository<UserEntity> {

    Optional<UserEntity> findByLogin(String login);

    List<UserEntity> findAllByApprovedIsTrue();

    @Query(value = "SELECT * from app_user u inner join user_role r on u.role_id = r.id or u.role_id is null ", nativeQuery = true)
    List<UserEntity> getAll();

}
