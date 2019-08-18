package com.relaysystem.ms.users.model;

import lombok.*;

@Data
public class CreateUserResponseModel {
    String firstName;
    String lastName;
    String email;
    String userId;
}
