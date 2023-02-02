package com.revature.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.revature.dtos.UpdateProfile;
import com.revature.exceptions.EmailAlreadyExists;
import com.revature.models.User;
import com.revature.repositories.UserRepository;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserService userService;

    @Test
    void testFindByCredentialsCorrect() {
        User user = User.builder().email("test@test.com").password("test").build();

        when(userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(Optional.of(user));

        Assertions.assertThat(userService.findByCredentials(user.getEmail(), user.getPassword())).isEqualTo(Optional.of(user));
    }

    @Test
    void testFindByCredentialsDoesNotExist() {
        User user = User.builder().email("test@test.com").password("test").build();

        when(userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(Optional.ofNullable(null));

        Assertions.assertThat(userService.findByCredentials(user.getEmail(), user.getPassword())).isEqualTo(Optional.ofNullable(null));
    }

    @Test
    void testGetAllUsersShouldNotContainUser() {
        User user = User.builder()
                .email("test@test.com")
                .password("password").build();
        User user1 = User.builder()
        .email("test1@test.com")
        .password("password").build();
        User user2 = User.builder()
                .email("test2@test.com")
                .password("password").build();

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(userRepo.findAll()).thenReturn(users);

        Assertions.assertThat(userService.getAllUsers(user)).doesNotContain(user);
    }

    @Test
    void testGetAllUsers() {
        User user = User.builder()
                .email("test@test.com")
                .password("password").build();
        User user1 = User.builder()
        .email("test1@test.com")
        .password("password").build();
        User user2 = User.builder()
                .email("test2@test.com")
                .password("password").build();

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(userRepo.findAll()).thenReturn(users);

        Assertions.assertThat(userService.getAllUsers(user)).hasSize(2);
    }

    @Test
    void testGetProfileInfo() {
        User user = User.builder()
        .email("test@test.com")
        .id(1)
        .password("password").build();

        when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));

        Assertions.assertThat(userService.getProfileInfo(user.getId())).isEqualTo(Optional.of(user));
    }

    @Test
    void testSave() throws EmailAlreadyExists {
        User user = User.builder()
                .email("test@test.com")
                .password("password").build();

        when(userRepo.existsByEmail(user.getEmail())).thenReturn(false);
                when(userRepo.save(Mockito.any(User.class))).thenReturn(user);

        User savedUser = userService.save(user);

        Assertions.assertThat(savedUser).isNotNull();
    }

    @Test
    void testUpdateProfileInfo() {

        //Arrange or Given
        User user = User.builder()
        .email("test@test.com")
        .password("password").build();

        UpdateProfile info = new UpdateProfile();
        info.setAbout("test");
        info.setImg("img");
        info.setUsername("test");

        User expected = User.builder()
        .email("test@test.com")
        .password("password").about("test").pic("img").username("test").build();

        //Act or When
        when(userService.updateProfileInfo(user, info)).thenReturn(expected);
        User actual = userService.updateProfileInfo(user, info);

        //Assert or Then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
