package com.revature.services;

import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.exceptions.EmailAlreadyExists;
import com.revature.models.User;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthService authService;

    @Test
    void testFindByCredentials() {
        User user = User.builder()
                .email("test@test.com")
                .password("password").build();

        Assertions.assertThat(authService.findByCredentials(user.getEmail(), user.getPassword())).isNotNull();

    }

    @Test
    public void AuthService_Register_ReturnsUser() throws EmailAlreadyExists {
        User user = User.builder()
                .email("test@test.com")
                .password("password").build();

        when(userService.save(Mockito.any(User.class))).thenReturn(user);

        User savedUser = authService.register(user);

        Assertions.assertThat(savedUser).isNotNull();
    }

}
