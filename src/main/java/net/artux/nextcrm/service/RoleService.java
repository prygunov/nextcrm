package net.artux.nextcrm.service;

import net.artux.nextcrm.model.RoleEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RoleService extends BaseService<RoleEntity> {

    public RoleService(JdbcTemplate template) {
        super(template);
    }
}
