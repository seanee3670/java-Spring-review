package com.db.dao;

import com.db.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserDaoTest {
    @Autowired

    private UserDao userDao = new UserDao(new JdbcTemplate());

    private User userA = new User("2", "A", "0000");
    private User userB = new User("3", "B", "0000");



    @BeforeEach
    void clear() {
        userDao.deleteAll();
    }

    @Test
    void addAndGet() {


        String id = "1";
        userDao.add(new User(id, "seeyun", "0000"));
        User user = userDao.get(id);
        assertEquals("seeyun", user.getName());
        assertEquals("0000", user.getPassword());
    }

    @Test
    void getAll() {
        userDao.add(userA);
        userDao.add(userB);

        assertEquals(2, userDao.getAll().size());
    }

}
