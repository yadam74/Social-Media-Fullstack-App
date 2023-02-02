package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.Authorized;
import com.revature.dtos.UserToFollow;
import com.revature.models.User;
import com.revature.services.FollowersService;
import com.revature.services.UserService;

@RestController
@RequestMapping("/users")
public class FollowersController {

    @Autowired
    private FollowersService fs;

    @Autowired
    private UserService us;

    @Authorized
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return ResponseEntity.ok(us.getAllUsers(user));
    }

    @Authorized
    @PutMapping("/follow")
    public ResponseEntity<List<User>> newFollow(@RequestBody UserToFollow userId, HttpSession session) {

        User user = (User) session.getAttribute("user");
        int id = userId.getUserId();

        return ResponseEntity.ok(fs.newFollow(user, id));
    }

    @Authorized
    @GetMapping("/following")
    public ResponseEntity<List<User>> getAllFollowing(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return ResponseEntity.ok(fs.allFollowing(user));
    }

    @Authorized
    @GetMapping("/followers")
    public ResponseEntity<List<User>> getAllFollowers(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return ResponseEntity.ok(fs.allFollowers(user));
    }
}
