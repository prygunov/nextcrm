package net.artux.nextcrm.controller.util;

import lombok.Data;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.order.OrderStatusEntity;
import net.artux.nextcrm.model.order.delivery.DeliveryEntity;
import net.artux.nextcrm.model.order.goods.GoodEntity;
import net.artux.nextcrm.model.order.payment.PaymentEntity;
import net.artux.nextcrm.model.user.UserEntity;

import java.util.List;

@Data
public class GoodPrint {

    private String name;
    private String code;
    private float price;
    private int quantity;
    private float totalPrice;

    public GoodPrint(GoodEntity goodEntity, int quantity) {
        name = goodEntity.getName();
        code = goodEntity.getCode();
        price = goodEntity.getPrice();
        this.quantity = quantity;
        totalPrice = price * quantity;
    }
}
