package com.email.sender.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UserSignUpRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

}
