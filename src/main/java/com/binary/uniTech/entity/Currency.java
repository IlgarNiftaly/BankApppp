package com.binary.uniTech.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_type")
    private String currencyType;

    @Column(name = "rate")
    private BigDecimal rate;

    @Column(name = "updateDate")
    private LocalDateTime updatedDate;

}
