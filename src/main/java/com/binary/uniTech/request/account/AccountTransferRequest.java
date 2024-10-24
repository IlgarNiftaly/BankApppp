package com.binary.uniTech.request.account;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransferRequest {

    @NonNull
    private String senderAccountNumber;

    @NonNull
    private BigDecimal senderBalance;


    @NonNull
    private String recipientAccountNumber;

//    @NonNull
//    private BigDecimal recipientBalance;
}
