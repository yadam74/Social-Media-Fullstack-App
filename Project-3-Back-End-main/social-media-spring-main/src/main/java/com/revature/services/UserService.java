package com.revature.services;

import com.revature.dtos.UpdateProfile;
import com.revature.exceptions.EmailAlreadyExists;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByCredentials(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public User save(User user) throws EmailAlreadyExists {
        if(!userRepository.existsByEmail(user.getEmail())) {
            return userRepository.save(user);
        } else {
            throw new EmailAlreadyExists("Email already exists");
        }
    }

    public Optional<User> getProfileInfo(int user_id) {
        return userRepository.findById(user_id);
    }

    public User updateProfileInfo(User user, UpdateProfile info) {
        user.setUsername(info.getUsername());
        user.setPic(info.getImg());
        user.setAbout(info.getAbout());

        return userRepository.save(user);

    }

    public List<User> getAllUsers(User user) {
        List<User> users = userRepository.findAll();
        users.remove(user);
        return users;
    }

    public User getUser(User user) {
        Optional<User> foundUser = userRepository.findById(user.getId());
        return foundUser.get();
    }
}
