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
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.models.Followers;
import com.revature.models.User;
import com.revature.repositories.FollowersRepository;
import com.revature.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class FollowersServiceTest {
    @Mock
    private FollowersRepository fr;

    @Mock
    private UserRepository ur;

    @InjectMocks
    private FollowersService fs;

    @Test
    void testNewFollowWithNoFollowing() {

        // Arrange or Given
        User user = new User();
        user.setId(1);
        User toFollow = new User();
        toFollow.setId(2);

        Followers follow = new Followers();
        List<User> expected = new ArrayList<>();
        expected.add(toFollow);
        follow.setUser(user);
        follow.setFollowing(expected);

        // Act or When
        when(ur.findById(toFollow.getId())).thenReturn(Optional.of(toFollow));
        when(fr.save(follow)).thenReturn(follow);
        List<User> actual = fs.newFollow(user, toFollow.getId());

        // Assert or Then
        Assertions.assertThat(expected).isEqualTo(actual);
    }

    @Test
    void testNewFollowWithEmptyFollowing() {
        // Arrange or Given
        User user = new User();
        user.setId(1);
        User toFollow = new User();
        toFollow.setId(2);

        Followers follow = new Followers();
        List<User> expected = new ArrayList<>();
        follow.setUser(user);
        follow.setFollowing(expected);

        // Act or When
        when(ur.findById(toFollow.getId())).thenReturn(Optional.of(toFollow));
        when(fr.existsByUser(user)).thenReturn(true);
        when(fr.findByUser(user)).thenReturn(follow);
        when(fr.save(follow)).thenReturn(follow);
        List<User> actual = fs.newFollow(user, toFollow.getId());

        // Assert or Then
        Assertions.assertThat(actual).contains(toFollow);
    }

    @Test
    void testNewFollowUnfollow() {
        // Arrange or Given
        User user = new User();
        user.setId(1);
        User toFollow = new User();
        toFollow.setId(2);

        Followers follow = new Followers();
        List<User> expected = new ArrayList<>();
        expected.add(toFollow);
        follow.setUser(user);
        follow.setFollowing(expected);

        // Act or When
        when(ur.findById(toFollow.getId())).thenReturn(Optional.of(toFollow));
        when(fr.existsByUser(user)).thenReturn(true);
        when(fr.findByUser(user)).thenReturn(follow);
        when(fr.save(follow)).thenReturn(follow);
        List<User> actual = fs.newFollow(user, toFollow.getId());

        // Assert or Then
        Assertions.assertThat(actual).isEmpty();
    }

    @Test
    void testAllFollowingWithFollowing() {
        User user = new User();
        user.setId(1);
        User user1 = new User();
        user1.setId(2);
        User user2 = new User();
        user2.setId(3);
        User user3 = new User();
        user3.setId(4);

        Followers follow = new Followers();
        List<User> expected = new ArrayList<>();
        expected.add(user1);
        expected.add(user2);
        expected.add(user3);
        follow.setUser(user);
        follow.setFollowing(expected);

        when(fr.existsByUser(user)).thenReturn(true);
        when(fr.findByUser(user)).thenReturn(follow);

        Assertions.assertThat(fs.allFollowing(user)).isEqualTo(expected);

    }

    @Test
    void testAllFollowers() {
        User user = new User();
        user.setId(1);
        User user1 = new User();
        user1.setId(2);
        User user2 = new User();
        user2.setId(3);
        User user3 = new User();
        user3.setId(4);

        Followers follow1 = new Followers();
        Followers follow2 = new Followers();
        Followers follow3 = new Followers();
        List<User> f1 = new ArrayList<>();
        List<User> f2 = new ArrayList<>();
        List<User> f3 = new ArrayList<>();
        f1.add(user);
        follow1.setUser(user1);
        follow1.setFollowing(f1);
        f2.add(user);
        follow2.setUser(user2);
        follow2.setFollowing(f2);
        f3.add(user);
        follow3.setUser(user3);
        follow3.setFollowing(f3);

        List<Followers> allF = new ArrayList<>();
        allF.add(follow1);
        allF.add(follow2);
        allF.add(follow3);

        when(fr.findAll()).thenReturn(allF);

        Assertions.assertThat(fs.allFollowers(user)).hasSize(3);
    }
}
