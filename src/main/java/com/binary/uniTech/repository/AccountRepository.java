package com.binary.uniTech.repository;

import com.binary.uniTech.entity.Account;
import com.binary.uniTech.enums.AccountStatus;
import com.binary.uniTech.wrapper.AccountWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("""
                select com.binary.uniTech.wrapper.AccountWrapper(a.id, a.accountNumber, a.balance, a.fkUserId, a.status)from Account a
            """)
    List<AccountWrapper> findAllAccountWrapper();

    Account findByAccountNumber(String accountNumber);
    Account findByFkUserId(Long userId);
    Account findByStatus(AccountStatus status);
    void deleteByAccountNumber(String accountNumber);

//    Account findByAccountNumberOrStatusIn(String accountNumber, String status);

}
