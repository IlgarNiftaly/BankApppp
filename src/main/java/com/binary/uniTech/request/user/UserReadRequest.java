package com.binary.uniTech.request.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReadRequest {

    private Long id;
    private String userName;
    private String userPin;
    private String email;

}
