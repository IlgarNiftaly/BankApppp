//package com.binary.uniTech.service.scheduledService;
//
//
//import com.binary.uniTech.entity.Currency;
//import com.binary.uniTech.feignClient.ExchangeRateResponse;
//import com.binary.uniTech.repository.CurrencyRepository;
//import com.binary.uniTech.request.currency.CurrencyRequest;
//import com.binary.uniTech.service.currency.CurrencyRateService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class ScheduledService {
//    private final CurrencyRepository currencyRepository;
//    private final CurrencyRateService currencyRateService;
//
//    public void saveCurrencyToDataBase(){
//        CurrencyRequest request = CurrencyRequest.builder()
//                .base("AZN")
//                .amount(BigDecimal.ONE)
//                .build();
//
//
//        ExchangeRateResponse currencyRate = currencyRateService.getCurrencyRate(request);
//        LocalDateTime date = currencyRate.getDate();
//        Map<String, BigDecimal> rates = currencyRate.getRates();
//
//        for (Map.Entry<String, BigDecimal> rate : rates.entrySet()) {
//
//            Currency currency = new Currency();
//            currency.setUpdatedDate(date);
//            currency.setRate(rate.getValue());
//            currency.setCurrencyType(rate.getKey());
//            currencyRepository.save(currency);
//        }
//    }
//
//    public void updateCurrencyRates() {
//        List<Currency> currencies = currencyRepository.findAll();
//
//        CurrencyRequest request = CurrencyRequest.builder()
//                .base("AZN")
//                .amount(BigDecimal.ONE)
//                .build();
//        ExchangeRateResponse currencyRate = currencyRateService.getCurrencyRate(request);
//
//        LocalDateTime date = currencyRate.getDate();
//        Map<String, BigDecimal> latestRates = currencyRate.getRates();
//
//        for (Currency currency : currencies) {
//            String currencyType = currency.getCurrencyType();
//            BigDecimal latestRate = latestRates.get(currencyType);
//
//            if (latestRate == null) {
//                currencyRepository.delete(currency);
//            } else {
//                currency.setUpdatedDate(date);
//                currency.setRate(latestRate);
//                currencyRepository.save(currency);
//            }
//        }
//    }
//
//}
