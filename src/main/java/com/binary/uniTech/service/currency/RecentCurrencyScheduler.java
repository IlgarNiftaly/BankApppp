package com.binary.uniTech.service.currency;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class RecentCurrencyScheduler {

    private final CurrencyRateService currencyRateService;

    @Scheduled(cron = "0/30 * * * * *")
    public void recentCurrencyData() {
        log.info("Inside recentCurrencyData : ");
        currencyRateService.getRecentCurrentData();
    }

}