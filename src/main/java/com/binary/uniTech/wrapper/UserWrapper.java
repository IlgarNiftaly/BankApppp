package com.binary.uniTech.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWrapper {

    private Long id;
    private String userName;
    private String email;
    private String password;
    private Boolean emailVerified;
    private Long fkAccountId;

    public UserWrapper(Long id, String userName){
        this.id = id;
        this.userName = userName;
    }
    public UserWrapper(Long id, String userName, String email){
        this.id = id;
        this.userName = userName;
        this.email = email;
    }
}
