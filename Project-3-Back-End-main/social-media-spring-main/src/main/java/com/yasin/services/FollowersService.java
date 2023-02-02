package com.revature.services;

import java.util.*;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Followers;
import com.revature.models.User;
import com.revature.repositories.FollowersRepository;
import com.revature.repositories.UserRepository;

@Service
public class FollowersService {

    @Autowired
    private FollowersRepository fr;

    @Autowired
    private UserRepository ur;

    public List<User> newFollow(User user, int userId) {

        Optional<User> toFollow = ur.findById(userId);

        if (!fr.existsByUser(user)) {
            Followers follow = new Followers();
            List<User> following = new ArrayList<>();

            follow.setUser(user);

            following.add(toFollow.get());
            follow.setFollowing(following);

            return fr.save(follow).getFollowing();
        } else {
            Followers followingBank = fr.findByUser(user);

            if (!followingBank.getFollowing().contains(toFollow.get())) {
                followingBank.getFollowing().add(toFollow.get());
            } else {
                followingBank.getFollowing().remove(toFollow.get());
            }

            return fr.save(followingBank).getFollowing();
        }
    }

    public List<User> allFollowing(User user) {
        if (!fr.existsByUser(user)) {
            Followers follow = new Followers();
            List<User> following = new ArrayList<>();

            follow.setUser(user);
            follow.setFollowing(following);

            return following;
        } else {
            return fr.findByUser(user).getFollowing();
        }
    }

    public List<User> allFollowers(User user) {
        List<Followers> allUsers = fr.findAll();
        List<User> followers = new ArrayList<>();

        for (Followers f : allUsers) {
            if (f.getFollowing().contains(user)) {
                followers.add(f.getUser());
            }
        }
        return followers;
    }

}
