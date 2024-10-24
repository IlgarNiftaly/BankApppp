package com.binary.uniTech.mapper;

import com.binary.uniTech.entity.Account;
import com.binary.uniTech.request.account.AccountCreateRequest;
import com.binary.uniTech.request.account.AccountUpdateRequest;
import com.binary.uniTech.response.account.AccountCreateResponse;
import com.binary.uniTech.response.account.AccountDeleteResponse;
import com.binary.uniTech.response.account.AccountReadResponse;
import com.binary.uniTech.response.account.AccountUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    Account requestToEntity(AccountCreateRequest createRequest);
    Account requestToEntity(AccountUpdateRequest updateRequest);

    AccountCreateResponse createToResponse(Account account);
    AccountReadResponse readToResponse(Account account);
    AccountUpdateResponse updateToResponse(Account account);
    AccountDeleteResponse deleteToResponse(Account account);

    List<AccountReadResponse> listEntityToListResponse(List<Account> account);
}
