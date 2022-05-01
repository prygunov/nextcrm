package net.artux.nextcrm.model.order;

import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Getter
public class OrderDto {

    private Long id;
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
        this.id = id;
        this.statusName = statusName;
        this.login = login;
        this.employeeId = employeeId;
        this.time = time;
        this.clientId = clientId;
        this.clientName = clientName;
        this.price = price;
    }
}
