package com.revature.services;

import com.revature.exceptions.EmailAlreadyExists;
import com.revature.models.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public Optional<User> findByCredentials(String email, String password) {
        return userService.findByCredentials(email, password);
    }

    public User register(User user) throws EmailAlreadyExists  {
        return userService.save(user);
    }
}
