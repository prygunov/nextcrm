package net.artux.nextcrm.model.order;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.user.UserEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "call")
@Entity
public class CallEntity extends BaseEntity {

    @ManyToOne
    @NotNull
    private OrderEntity order;
    @ManyToOne
    @NotNull
    private UserEntity author;
    private String number;
    private boolean income;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date time;
    private String result;

}
