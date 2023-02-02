package com.revature.controllers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.services.AuthService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AuthController.class)
@RequestMapping("/auth")
public class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    // @Test
    // public void testSuccessfulLogin() throws Exception {

    // LoginRequest login = new LoginRequest("test@test.com", "test");
    // HttpSession session = new MockHttpSession();
    // User user =
    // User.builder().email(login.getEmail()).password(login.getPassword()).build();

    // when(authService.findByCredentials(login.getEmail(),
    // login.getPassword())).thenReturn(Optional.of(user));

    // mockMvc.perform(MockMvcRequestBuilders.post("/auth/login"))
    // .andExpect(MockMvcResultMatchers.status().isOk());
    // }

    // @Test
    // void testLogout() {

    // }

    // @Test
    // void testRegister() {

    // }
}
