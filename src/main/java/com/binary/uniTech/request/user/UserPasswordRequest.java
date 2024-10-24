package com.binary.uniTech.request.user;


import jakarta.validation.constraints.Email;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordRequest {

    @Email
    public String email;

}
