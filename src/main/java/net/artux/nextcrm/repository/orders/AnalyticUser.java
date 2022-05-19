package net.artux.nextcrm.repository.orders;

public interface AnalyticUser {

    Double getAvg();
    Long getCount();
    Double getProfit();
    String getFirstname();
    String getLastname();
    Long getId();

    default String fullName(){
        return getFirstname() + " " + getLastname();
    }

}
