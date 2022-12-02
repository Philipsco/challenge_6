package com.philsco.challenge4.module.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddUserDTO {
    private String username;
    private String email;
    private String password;
}
