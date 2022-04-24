package net.artux.nextcrm.model.order;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.order.delivery.DeliveryEntity;
import net.artux.nextcrm.model.order.goods.GoodEntity;
import net.artux.nextcrm.model.order.payment.PaymentEntity;
import net.artux.nextcrm.model.user.UserEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Entity
public class OrderDto extends BaseEntity {

    private String statusName;
    private String login;
    private Long employeeId;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date time;
    private Long clientId;
    private String clientName;
    private Double price;

    public OrderDto(){

    }

    public OrderDto(Long id, String statusName, String login, Long employeeId, Date time, Long clientId, String clientName, Double price) {
        super(id);
        this.statusName = statusName;
        this.login = login;
        this.employeeId = employeeId;
        this.time = time;
        this.clientId = clientId;
        this.clientName = clientName;
        this.price = price;
    }
}
