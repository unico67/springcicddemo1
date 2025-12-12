package com.example.springcicddemo1;

import com.example.springcicddemo1.entity.Friend;
import com.example.springcicddemo1.repository.FriendRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class FriendRepositoryTest {
    @Autowired
    private FriendRepository fr;
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
