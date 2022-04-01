package net.artux.nextcrm.repository;

import net.artux.nextcrm.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CRepository<E extends BaseEntity> extends JpaRepository<E, Long> {

}
