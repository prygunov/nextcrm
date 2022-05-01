package net.artux.nextcrm.model.costs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.order.OrderEntity;
import net.artux.nextcrm.model.user.UserEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "cost")
@Entity
public class CostEntity extends BaseEntity {

    @ManyToOne
    private CostArticleEntity article;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startPeriod;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endPeriod;

    private String comment;
    @ManyToOne
    private UserEntity employee;

    private double sum;

    @ManyToOne
    private OrderEntity order;
}
