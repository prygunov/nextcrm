package net.artux.nextcrm.model.order;

import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public interface OrderProjection {

    Long getId();
    String getStatusName();
    String getEmployeeName();
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    Date getTime();
    String getClientName();
    Double getSum();

}
