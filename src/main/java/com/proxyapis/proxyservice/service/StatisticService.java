package com.proxyapis.proxyservice.service;

public interface StatisticService {
    long getSuccess();
    long getError();
    long getRateLimit();
    long getDuration();
    long getTotal();
    void reset();
}
