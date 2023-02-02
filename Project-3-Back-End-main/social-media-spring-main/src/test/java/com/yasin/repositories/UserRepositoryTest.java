package com.revature.repositories;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.revature.models.User;

// @DataJpaTest
// @AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
// public class UserRepositoryTest {

//     @Autowired
//     private UserRepository userRepo;

//     @Test
//     void UserRepository_SaveAll_ReturnSavedUser() {
//         // Arrange
//         User user = User.builder()
//                 .email("test@test.com")
//                 .password("password")
//                 .build();
//         // Act
//         User savedUser = userRepo.save(user);
//         // Assert
//         Assertions.assertThat(savedUser).isNotNull();
//         Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
//     }

//     @Test
//     public void UserRepository_GetAll_ReturnMoreThanOneUser() {
//         User user = User.builder()
//                 .email("test@test.com")
//                 .password("password")
//                 .firstName("Bob")
//                 .build();
//         User user2 = User.builder()
//                 .email("test2@test2.com")
//                 .password("password")
//                 .firstName("Joe")
//                 .build();

//         userRepo.save(user);
//         userRepo.save(user2);

//         List<User> userList = userRepo.findAll();
//         Assertions.assertThat(userList).isNotNull();
//         Assertions.assertThat(userList.size()).isEqualTo(8);
//     }

//     @Test
//     public void UserRepository_FindById_ReturnsUser() {
//         User user = User.builder()
//                 .email("test@test.com")
//                 .password("password")
//                 .firstName("Bob")
//                 .build();

//         userRepo.save(user);

//         User userById = userRepo.findById(user.getId()).get();
//         Assertions.assertThat(userById).isNotNull();
//     }

//     @Test
//     public void UserRepository_FindByEmailAndPassword_ReturnUser() {
//         User user = User.builder().email("test@test.com").password("password").build();

//         userRepo.save(user);

//         User returnedUser = userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword()).get();

//         Assertions.assertThat(returnedUser.getEmail()).isNotNull();
//     }

//     @Test
//     public void UserRepository_DeleteById_ReturnUserIsEmpty() {
//         User user = User.builder()
//                 .email("test@test.com")
//                 .password("password")
//                 .firstName("Bob")
//                 .build();

//         userRepo.save(user);

//         userRepo.deleteById(user.getId());

//         Optional<User> returnedUser = userRepo.findById(user.getId());
//         Assertions.assertThat(returnedUser).isEmpty();
//     }

// }
