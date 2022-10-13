package com.foxminded.aprihodko.task10.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.BaseDaoTest;
import com.foxminded.aprihodko.task10.dao.UserDao;
import com.foxminded.aprihodko.task10.models.Role;
import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserDaoImplTest extends BaseDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldFindTeacherById() throws SQLException {
        User expected = new Teacher(100L, "john", 100L, "12345678");
        User actual = userDao.findById(100L).orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldFindStudentById() throws SQLException {
        User expected = new Student(101L, "peter", 100L, "12345678");
        User actual = userDao.findById(101L).orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldFindAllUsers() throws SQLException {
        List<User> expected = Arrays.asList(new Student(101L, "peter", 100L, "12345678"),
                new Student(103L, "bob", 101L, "12345678"),
                new User(104L, "none", UserType.NONE, Role.ADMIN, "12345678"),
                new Teacher(102L, "alice", 101L, "12345678"), new Teacher(100L, "john", 100L, "12345678"));
        List<User> actual = userDao.findAll();
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    @Transactional
    void shoudlDeleteTeacherById() throws SQLException {
        userDao.deleteById(100L);
        Optional<User> shouldBeEmpty = userDao.findById(100L);
        assertTrue(shouldBeEmpty.isEmpty());
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    @Transactional
    void shoudlDeleteStudentById() throws SQLException {
        userDao.deleteById(101L);
        Optional<User> shouldBeEmpty = userDao.findById(5L);
        assertTrue(shouldBeEmpty.isEmpty());
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shoudlFindTeaherByName() throws SQLException {
        User expected = new Teacher(100L, "john", 100L, "12345678");
        User actual = userDao.findByName("john").orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shoudlFindStudentByName() throws SQLException {
        User expected = new Student(101L, "peter", 100L, "12345678");
        User actual = userDao.findByName("peter").orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldFindTeachersByType() throws SQLException {
        List<User> expected = Arrays.asList(new Teacher(100L, "john", 100L, "12345678"),
                new Teacher(102L, "alice", 101L, "12345678"));
        List<User> actual = userDao.findByType(UserType.TEACHER);
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldFindStudentsByType() throws SQLException {
        List<User> expected = Arrays.asList(new Student(101L, "peter", 100L, "12345678"),
                new Student(103L, "bob", 101L, "12345678"));
        List<User> actual = userDao.findByType(UserType.STUDENT);
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldCreateTeacher() throws SQLException {
        User expected = new Teacher("new Teacher", 104L, "12345678");
        User actual = userDao.save(expected);
        User byId = userDao.findById(2L).orElseThrow();
        assertNotNull(actual.getId());
        expected.setId(actual.getId());
        assertEquals(expected, actual);
        assertEquals(expected, byId);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldCreateStudent() throws SQLException {
        User expected = new Student("john_new", 101L, "12345678");
        User actual = userDao.save(expected);
        User byId = userDao.findById(1L).orElseThrow();
        assertNotNull(actual.getId());
        expected.setId(actual.getId());
        assertEquals(expected, actual);
        assertEquals(expected, byId);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldUpdateStudent() throws SQLException {
        User expected = userDao.findById(101L).orElseThrow();
        expected = new Student(101L, "update_Stud", 102L, "12345678");
        User actual = userDao.save(expected);
        assertNotNull(actual.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldUpdateUser() throws SQLException {
        User update = userDao.findById(104L).orElseThrow();
        update = new User(104L, "new Teach", UserType.NONE, Role.ADMIN, "12345678");
        User actual = userDao.save(update);
        assertNotNull(actual.getId());
        assertEquals(update, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldCreateUserNone() throws SQLException {
        User expected = new User("NONE", UserType.NONE, Role.ADMIN, "12345678");
        User actual = userDao.save(expected);
        User byId = userDao.findById(3L).orElseThrow();
        assertNotNull(actual.getId());
        expected.setId(actual.getId());
        assertEquals(expected, actual);
        assertEquals(expected, byId);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldFindTeachersByCourseId() throws SQLException {
        List<Teacher> expected = Arrays.asList(new Teacher(100L, "john", 100L, "12345678"));
        List<Teacher> actual = userDao.findTeacherByCourseId(100L);
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldFindStudentByGroupId() throws SQLException {
        List<Student> expected = Arrays.asList(new Student(101L, "peter", 100L, "12345678"));
        List<Student> actual = userDao.findStudentByGroupId(100L);
        assertEquals(expected, actual);
    }
}