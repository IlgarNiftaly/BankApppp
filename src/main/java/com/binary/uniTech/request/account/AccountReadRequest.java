package com.binary.uniTech.request.account;

import com.binary.uniTech.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountReadRequest {

    private Long id;
    private String accountNumber;
    private Long fkUserId;
    private AccountStatus status;

}
