package com.example.springcicddemo1;

import com.example.springcicddemo1.entity.Friend;
import com.example.springcicddemo1.repository.FriendRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class FriendRepositoryTest {
    @Autowired
    private FriendRepository fr;

    @Container
    static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:8.0");

    @Test
    @Rollback(false)
    @Transactional
    void save() {
        List<Friend> friends = fr.findAll();
        if (friends.size() != 3) {
            Friend entity = new Friend();
            entity.setName("둘리");
            fr.save(entity);
            entity = new Friend();
            entity.setName("또치");
            fr.save(entity);
            entity = new Friend();
            entity.setName("도우너");
            fr.save(entity);
        }
    }
    @Test
    void getAllUsers_shouldReturnInitialUsers() {
        List<Friend> friends = fr.findAll();
        assertNotNull(friends);
        assertEquals(3, friends.size());
    }
}
