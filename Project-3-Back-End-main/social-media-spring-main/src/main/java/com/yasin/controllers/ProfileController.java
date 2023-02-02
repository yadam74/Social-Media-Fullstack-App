package com.revature.controllers;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.Authorized;
import com.revature.dtos.UpdateProfile;
import com.revature.models.User;
import com.revature.services.UserService;

@RestController
@RequestMapping("/profile")
// @CrossOrigin(origins = {"*"}, allowCredentials = "true")
public class ProfileController {

    @Autowired
    private UserService us;

    @Authorized
    @GetMapping
    public ResponseEntity<User> getProfileInfo(HttpSession session) {

        User user = (User) session.getAttribute("user");
        Optional<User> optional = us.getProfileInfo(user.getId());

        return ResponseEntity.ok(optional.get());
    }

    @Authorized
    @PatchMapping("/update")
    public ResponseEntity<User> updateProfile(@RequestBody UpdateProfile info, HttpSession session) {
        User user = (User) session.getAttribute("user");

        User newUser = us.updateProfileInfo(user, info);

        session.setAttribute("user", newUser);

        return ResponseEntity.ok(newUser);
    }
}