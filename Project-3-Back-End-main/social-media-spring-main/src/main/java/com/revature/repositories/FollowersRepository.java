package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Followers;
import com.revature.models.User;

public interface FollowersRepository extends JpaRepository<Followers, Integer> {
    Followers findByUser(User user);

    Boolean existsByUser(User user);
}
