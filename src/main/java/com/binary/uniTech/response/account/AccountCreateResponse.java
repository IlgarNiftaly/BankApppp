package com.binary.uniTech.response.account;

import com.binary.uniTech.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateResponse {

    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private Long fkUserId;
    private AccountStatus status;

}
