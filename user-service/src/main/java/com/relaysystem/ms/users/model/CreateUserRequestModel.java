package com.relaysystem.ms.users.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateUserRequestModel {
    @NotNull(message = "First name can't be null")
    @Size(min = 2, message = "First name can't be less than 2 symbols")
    private String firstName;
    @NotNull(message = "Last name can't be null")
    @Size(min = 2, message = "Last name can't be less than 2 symbols")
    private String lastName;
    @NotNull(message = "Password can't be null")
    @Size(min = 8, max = 16, message = "Password must be equal or greater than 8 characters and less than 16 characters")
    private String password;
    @NotNull(message = "Email can't be null")
    @Email
    private String email;
}
