package com.relay.controllers;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import com.relay.service.UserService;

public class UserControllerTest extends AbstractControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    // @Before
    // public void setUp() throws Exception {
    //
    // super.setUp();
    //
    // mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    // List<User> users = new ArrayList<>();
    // users.add(new User("Test user"));
    //
    // when(userService.findAll()).thenReturn(users);
    // }
    //
    // @Test
    // public void should200Status() throws Exception {
    //
    // mockMvc.perform(get("/users")).andExpect(status().isOk());
    //
    // }

}
