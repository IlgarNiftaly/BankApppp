package com.binary.uniTech.controller;


import com.binary.uniTech.response.currency.CurrencyResponse;
import com.binary.uniTech.service.currency.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@RestController
@RequestMapping("/api/v1/currencies")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyRateService currencyRateService;


    @GetMapping("/latest")
    public ResponseEntity<CurrencyResponse> currencyRates() {
        return ResponseEntity.status(HttpStatus.OK).body(currencyRateService.allCurrencyRates());
    }

    @GetMapping("/convert")
    public ResponseEntity<BigDecimal> convertAmount(@RequestParam BigDecimal amount, @RequestParam String source,
                                                @RequestParam String target, @RequestParam LocalDate date) {
        return ResponseEntity.status(HttpStatus.OK).body(currencyRateService.convertAmount(amount, source, target, date));
    }

    @GetMapping("/specific")
    public ResponseEntity<BigDecimal> getExchangeRate(@RequestParam String source, @RequestParam String target,
                                                      @RequestParam LocalDate date) {
        return ResponseEntity.status(HttpStatus.OK).body(currencyRateService.getSpecificExchangeRate(source, target, date));
    }

}
