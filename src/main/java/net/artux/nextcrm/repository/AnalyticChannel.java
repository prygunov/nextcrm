package net.artux.nextcrm.repository;

public interface AnalyticChannel {

    Long getCount();
    Long getClosed();
    Double getPercent();
    String getName();

}
