package com.binary.uniTech.repository;

import com.binary.uniTech.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

//    List<CurrencyWrapper> findAllCurrencyWrapper();
    List<Currency> findByUpdatedDateBefore(LocalDateTime localDateTime);


}
