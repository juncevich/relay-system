package com.relay.controllers;

import com.relay.model.User;
import com.relay.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends AbstractControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        UserController userController = new UserController(userService);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        List<User> users = new ArrayList<>();
        users.add(new User("Test user"));

        when(userService.findAll()).thenReturn(users);
    }

    @Test
    public void should200Status() throws Exception {

        mockMvc.perform(get("/users")).andExpect(status().isOk());

    }
}