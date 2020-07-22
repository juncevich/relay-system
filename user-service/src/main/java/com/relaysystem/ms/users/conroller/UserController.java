package com.relaysystem.ms.users.conroller;

import com.relaysystem.ms.users.model.CreateUserRequestModel;
import com.relaysystem.ms.users.model.CreateUserResponseModel;
import com.relaysystem.ms.users.service.UserService;
import com.relaysystem.ms.users.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/status/check")
    public String status() {
        return "Working";
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE
    },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            })
    public CreateUserResponseModel createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
        var mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        var userDto    = mapper.map(userDetails, UserDto.class);
        var createUser = userService.createUser(userDto);

        return mapper.map(createUser, CreateUserResponseModel.class);
    }
}
