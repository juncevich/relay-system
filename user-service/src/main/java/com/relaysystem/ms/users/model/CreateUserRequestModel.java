package com.relaysystem.ms.users.model;

import lombok.*;

import javax.validation.constraints.*;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserRequestModel {
    @NotNull(message = "First name can't be null")
    @Size(min = 2, message = "First name can't be less than 2 symbols")
    final String firstName;
    @NotNull(message = "Last name can't be null")
    @Size(min = 2, message = "Last name can't be less than 2 symbols")
    final String lastName;
    @NotNull(message = "Password can't be null")
    @Size(min = 8, max = 16, message = "Password must be equal or greater than 8 characters and less than 16 characters")
    final String password;
    @NotNull(message = "Email can't be null")
    @Email
    final String email;
}
