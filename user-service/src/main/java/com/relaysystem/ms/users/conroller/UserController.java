package com.relaysystem.ms.users.conroller;

import com.relaysystem.ms.users.model.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/status/check")
    public String status() {
        return "Working";
    }

    @PostMapping
    public String createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
        return "User created";
    }
}
