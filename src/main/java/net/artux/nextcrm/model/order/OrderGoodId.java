package net.artux.nextcrm.model.order;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@Embeddable
public class OrderGoodId implements Serializable {

    private Long order;
    private Long good;
}
