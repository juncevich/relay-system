package com.relaysystem.ms.users.shared;

import lombok.*;

import java.io.*;

@Data
@RequiredArgsConstructor
public class UserDto implements Serializable {

    String firstName;
    String lastName;
    String password;
    String email;
    String userId;
    String encryptedPassword;
}
