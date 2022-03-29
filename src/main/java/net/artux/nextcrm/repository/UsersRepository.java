package net.artux.nextcrm.repository;

import net.artux.nextcrm.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByLogin(String login);

    @Query(value = "SELECT * from app_user u inner join user_role r on u.role_id = r.id or u.role_id is null ", nativeQuery = true)
    List<UserEntity> getAll();

}
