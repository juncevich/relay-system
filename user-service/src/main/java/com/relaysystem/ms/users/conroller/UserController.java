package com.relaysystem.ms.users.conroller;

import com.relaysystem.ms.users.model.*;
import com.relaysystem.ms.users.service.*;
import com.relaysystem.ms.users.shared.*;
import org.modelmapper.*;
import org.modelmapper.convention.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/status/check")
    public String status() {
        return "Working";
    }

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = mapper.map(userDetails, UserDto.class);
        UserDto createUser = userService.createUser(userDto);

        CreateUserResponseModel body = mapper.map(createUser, CreateUserResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
