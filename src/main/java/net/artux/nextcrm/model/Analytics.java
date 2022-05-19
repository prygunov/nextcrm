package net.artux.nextcrm.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Data
@Getter
@RequiredArgsConstructor
public class Analytics {

    private Double revenue;
    private Double profit;
    private Double expense;
    private Double average;
    private Long count;

    public Analytics(Double revenue, Double profit, Double expense) {
        this.revenue = revenue;
        this.profit = profit;
        this.expense = expense;
    }
}
