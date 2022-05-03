package net.artux.nextcrm.repository;

import net.artux.nextcrm.model.CompanyInfoEntity;
import net.artux.nextcrm.model.appeal.AppealEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyInfoRepository extends CRepository<CompanyInfoEntity> {

    @Query(value = "select * from company_info limit(1)", nativeQuery = true)
    Optional<CompanyInfoEntity> findFirst();
}
