package net.artux.nextcrm.controller.util;

import lombok.Data;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.order.OrderStatusEntity;
import net.artux.nextcrm.model.order.delivery.DeliveryEntity;
import net.artux.nextcrm.model.order.goods.GoodEntity;
import net.artux.nextcrm.model.order.payment.PaymentEntity;
import net.artux.nextcrm.model.user.UserEntity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
public class OrderPrint {

    private Long id;
    private ClientEntity client;
    private OrderStatusEntity status;
    private DeliveryEntity delivery;
    private List<PaymentEntity> payments;
    private UserEntity employee;
    private List<GoodPrint> goods;
    private Date time;
    private float sumGoods;
    private float totalPrice;

    public OrderPrint(OrderEntity order) {
        id = order.getId();
        client = order.getClient();
        status = order.getStatus();
        delivery = order.getDelivery();
        payments = order.getPayments();
        employee = order.getEmployee();
        time = order.getTime();
        goods = new ArrayList<>();
        order.getGoods().stream().collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum)).forEach(new BiConsumer<GoodEntity, Integer>() {
            @Override
            public void accept(GoodEntity goodEntity, Integer aLong) {
                GoodPrint good = new GoodPrint(goodEntity, aLong);
                goods.add(good);
                sumGoods += good.getTotalPrice();
            }
        });
        if (delivery!=null)
            totalPrice = sumGoods + delivery.getType().getPrice();
        else totalPrice = sumGoods;
    }
}
