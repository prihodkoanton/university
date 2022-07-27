package com.foxminded.aprihodko.task10.dao.services.impl;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.foxminded.aprihodko.task10.BaseDaoTest;
import com.foxminded.aprihodko.task10.dao.UserDao;
import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;
import com.foxminded.aprihodko.task10.services.impl.UserServiceImpl;

@SpringBootTest(classes = { UserServiceImpl.class })
class UserServiceImplTest extends BaseDaoTest {

    @MockBean
    UserDao userDao;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void shouldFindById() throws SQLException {
        User user = new Teacher(100L, "john", 100L);
        when(userDao.findById(100L)).thenReturn(Optional.of(user));
        Optional<User> expected = userDao.findById(100L);
        Optional<User> actual = userServiceImpl.findById(100L);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAll() throws SQLException {
        List<User> users = Arrays.asList(new User(100L, "petr", UserType.NONE), new Teacher(101L, "john", 101L),
                new Student(102L, "alice", 102L));
        when(userDao.findAll()).thenReturn(users);
        List<User> expected = userDao.findAll();
        List<User> actual = userServiceImpl.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteById() throws SQLException {
        userServiceImpl.deleteById(100L);
        verify(userDao).deleteById(100L);
    }

    @Test
    void shouldNotDeleteById() throws SQLException {
        when(userDao.findById(10L)).thenReturn(null);
        doThrow(new SQLException()).when(userDao).deleteById(10L);
        Exception actual = assertThrows(SQLException.class, () -> userServiceImpl.deleteById(10L));
        Exception expected = assertThrows(SQLException.class, () -> userDao.deleteById(10L));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindUserByName() throws SQLException {
        User user = new User(100L, "john", UserType.NONE);
        when(userDao.findByName("john")).thenReturn(Optional.of(user));
        Optional<User> expected = userDao.findByName("john");
        Optional<User> actual = userServiceImpl.findByName("john");
        assertEquals(expected, actual);
    }

    @Test
    void shouldCreateUser() throws SQLException {
        User user = new User(100L, "john", UserType.NONE);
        when(userDao.save(user, 100L)).thenReturn(user);
        User expected = userDao.save(user,100L);
        User actual = userDao.save(user,100L);
        assertNotNull(actual.getId());
        user.setId(actual.getId());
        assertEquals(expected, actual);
    }

}
