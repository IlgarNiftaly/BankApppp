package com.binary.uniTech.request.currency;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRequest {

    private Long id;
    private String currencyType;
    private BigDecimal rate;

}
