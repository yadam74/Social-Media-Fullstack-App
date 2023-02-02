package com.revature.controllers;

import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.exceptions.EmailAlreadyExists;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService us;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Optional<User> optional = authService.findByCredentials(loginRequest.getEmail(), loginRequest.getPassword());

        if (!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        session.setAttribute("user", optional.get());

        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("user");

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) throws EmailAlreadyExists {
        User created = new User(0,
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                "Pick a cool username!", "upload a profile picture!", "Write about yourself!");

        try {
            User user = authService.register(created);
            return ResponseEntity.ok().body(user);
        } catch (EmailAlreadyExists e) {
            e.getMessage();
            return ResponseEntity.badRequest().body(null);
        }
        
    }

    @PostMapping("/user")
    public ResponseEntity<User> getUser(HttpSession session) {
        User userToGet = (User) session.getAttribute("user");
        User user = us.getUser(userToGet);
        return ResponseEntity.ok().body(user);
    }
}
