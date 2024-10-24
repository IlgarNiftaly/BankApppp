package com.binary.uniTech.entity;

import com.binary.uniTech.validation.EmailValid;
import com.binary.uniTech.validation.FinValid;
import com.binary.uniTech.validation.InfoValid;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.EnableMBeanExport;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @InfoValid
    @Column(name = "user_name")
    private String userName;

    @FinValid
    @Column(name = "user_pin")
    private String userPin;

    @EmailValid
    @Column(name = "email")
    private String email;

    @InfoValid
    @Column(name = "password")
    private String password;

    @Column(name = "email_verified", nullable = false)
    private Boolean emailVerified = false;

    @Column(name = "fk_account_id")
    private Long fkAccountId;

}
