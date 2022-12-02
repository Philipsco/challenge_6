package com.philsco.challenge4.module.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserDTO {
    private Integer userId;
    private String username;
    private String email;
    private String password;
}
