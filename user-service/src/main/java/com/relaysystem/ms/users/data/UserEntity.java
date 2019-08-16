package com.relaysystem.ms.users.data;

import lombok.*;

import javax.persistence.*;
import java.io.*;

@Entity
@Table(name = "users")
@Data
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue
    long id;
    @Column(nullable = false, length = 50)
    String firstName;
    @Column(nullable = false, length = 50)
    String lastName;
    @Column(nullable = false, length = 120, unique = true)
    String email;
    @Column(nullable = false, unique = true)
    String userId;
    @Column(nullable = false)
    String encryptedPassword;
}
