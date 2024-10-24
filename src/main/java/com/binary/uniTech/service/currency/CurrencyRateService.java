package com.binary.uniTech.service.currency;


import com.binary.uniTech.entity.Currency;
import com.binary.uniTech.exception.IllegalArgumentException;
import com.binary.uniTech.exception.error.ErrorMessage;
import com.binary.uniTech.repository.CurrencyRepository;
import com.binary.uniTech.response.currency.CurrencyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyRateService {

    @Value("${currency.api.url}")
    private String url;

    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;

    public CurrencyResponse allCurrencyRates() {
        return restTemplate.getForObject(url, CurrencyResponse.class);
    }

    public BigDecimal getSpecificExchangeRate(String source, String target, LocalDate date) {
        CurrencyResponse response = restTemplate.getForObject(url, CurrencyResponse.class);
        if (response == null) {
            throw new IllegalArgumentException(ErrorMessage.DATA_NOT_FOUND);
        }
        BigDecimal sourceRate = BigDecimal.valueOf(response.getRates().get(source));
        BigDecimal targetRate = BigDecimal.valueOf(response.getRates().get(target));
        if (sourceRate.compareTo(BigDecimal.ZERO) == 0 || targetRate.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PAIR);
        }
        BigDecimal divide = targetRate.divide(sourceRate, 2, RoundingMode.HALF_UP);
        Currency currency = new Currency();
        currency.setCurrencyType(source);
        currency.setRate(divide);
        currency.setUpdatedDate(LocalDateTime.now());
        currencyRepository.save(currency);
        return divide;
    }

    public BigDecimal convertAmount(BigDecimal amount, String source, String target, LocalDate date) {
        BigDecimal exchangeRate = getSpecificExchangeRate(source, target, date);
        if (exchangeRate.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PAIR);
        }
        return amount.divide(exchangeRate, 2, RoundingMode.HALF_UP);
    }

    public void getRecentCurrentData() {
        LocalDateTime now = LocalDateTime.now().minusMinutes(1);
        List<Currency> reminders = currencyRepository.findByUpdatedDateBefore(now);
        for (Currency currency : reminders) {
            currency.setUpdatedDate(LocalDateTime.now());
            currencyRepository.save(currency);
        }
    }


}
