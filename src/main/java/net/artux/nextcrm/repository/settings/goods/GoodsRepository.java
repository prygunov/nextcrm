package net.artux.nextcrm.repository.settings.goods;

import net.artux.nextcrm.model.order.goods.GoodEntity;
import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.repository.CRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GoodsRepository extends CRepository<GoodEntity> {

}
