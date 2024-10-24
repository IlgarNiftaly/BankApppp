package com.binary.uniTech.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyWrapper {

    private Long id;
    private String currencyType;
    private BigDecimal rate;
    private LocalDateTime updatedDate;

    public CurrencyWrapper(Long id, String currencyType) {
        this.id = id;
        this.currencyType = currencyType;
    }
}
