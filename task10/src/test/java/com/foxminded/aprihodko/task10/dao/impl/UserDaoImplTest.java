package com.foxminded.aprihodko.task10.dao.impl;

import com.foxminded.aprihodko.task10.BaseDaoTest;
import com.foxminded.aprihodko.task10.dao.UserDao;
import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import javax.annotation.PostConstruct;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserDaoImplTest extends BaseDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private UserDao userDao;

    @PostConstruct
    void init() {
        userDao = new UserDaoImpl(jdbcTemplate);
    }

    @Test
    @Sql(scripts = {"/sql/clear_tables.sql", "/sql/user_test_data.sql"})
    void shouldFindTeachersByType() throws SQLException {
        List<User> expected = Arrays.asList(
                new Teacher(100L, "john", 100L),
                new Teacher(102L, "alice", 101L)
        );
        List<User> actual = userDao.findByType(UserType.TEACHER);
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = {"/sql/clear_tables.sql", "/sql/user_test_data.sql"})
    void shouldFindStudentsByType() throws SQLException {
        List<User> expected = Arrays.asList(
                new Student(101L, "peter", 100L),
                new Student(103L, "bob", 101L)
        );
        List<User> actual = userDao.findByType(UserType.STUDENT);
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = {"/sql/clear_tables.sql", "/sql/user_test_data.sql"})
    void shouldFindTeachersByCourseId() throws SQLException {
        List<Teacher> expected = Arrays.asList(new Teacher(100L, "john", 100L));
        List<Teacher> actual = userDao.findTeacherByCourseId(100L);
        assertEquals(expected, actual);
    }
}