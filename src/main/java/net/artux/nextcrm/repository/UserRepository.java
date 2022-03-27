package net.artux.nextcrm.repository;

import net.artux.nextcrm.model.UserEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository extends BasicRepository<UserEntity> {

    public UserRepository(JdbcTemplate template) {
        super(template, UserEntity.class);
    }

    public Optional<UserEntity> findByLogin(String login){
        UserEntity entity = null;
        try {
            entity = template.queryForObject("SELECT * FROM " + tableName + " WHERE login=" + login, rowMapper);
        }catch (Exception ignored){}

        return Optional.ofNullable(entity);
    }

}
