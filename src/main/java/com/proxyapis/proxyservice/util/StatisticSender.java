package com.proxyapis.proxyservice.util;

import com.proxyapis.proxyservice.client.StatisticServiceClient;
import com.proxyapis.proxyservice.dto.StatisticDTO;
import com.proxyapis.proxyservice.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableAsync
@Configuration
@EnableScheduling
@Slf4j
public class StatisticSender {

    private static final Logger logger = LoggerFactory.getLogger(StatisticSender.class);
    private final StatisticServiceClient statisticClient;
    private final StatisticService statisticService;

    public StatisticSender(StatisticServiceClient statisticClient,
                           StatisticService statisticService) {
        this.statisticClient = statisticClient;
        this.statisticService = statisticService;
    }

    @Scheduled(fixedDelay = 10000)
    public void sendStatistics() {
        final StatisticDTO statistic = new StatisticDTO(
                statisticService.getTotal(),
                statisticService.getSuccess(),
                statisticService.getError(),
                statisticService.getRateLimit(),
                statisticService.getDuration(),
                new Date()
        );

        logger.info("StatisticSender getTotal: "
                + statisticService.getTotal());
        logger.info("StatisticSender getSuccess: "
                + statisticService.getSuccess());
        logger.info("StatisticSender getError: "
                + statisticService.getError());
        logger.info("StatisticSender getRateLimit: "
                + statisticService.getRateLimit());
        logger.info("StatisticSender getDuration: "
                + statisticService.getDuration());

        statisticClient.create(statistic);
        statisticService.reset();
    }
}