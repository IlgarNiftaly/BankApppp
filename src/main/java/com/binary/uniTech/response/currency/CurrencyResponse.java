package com.binary.uniTech.response.currency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyResponse {

    private Long id;
    private String currencyType;
    private Map<String, Double> rates;

}
