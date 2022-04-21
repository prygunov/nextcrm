package net.artux.nextcrm.repository;

import net.artux.nextcrm.model.appeal.AppealEntity;
import net.artux.nextcrm.model.client.ClientEntity;

import java.util.List;

public interface AppealsRepository extends CRepository<AppealEntity> {

    List<AppealEntity> findAllByStatus_EndingIsFalse();

}
