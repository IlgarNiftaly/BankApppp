package com.binary.uniTech.response.account;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransferResponse {

    private String payAccountNumber;
    private BigDecimal peyBalance;

    private String transferAccountNumber;
    private BigDecimal transferBalance;

}
