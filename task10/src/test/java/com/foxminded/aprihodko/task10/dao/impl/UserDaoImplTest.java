package com.foxminded.aprihodko.task10.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import com.foxminded.aprihodko.task10.BaseDaoTest;
import com.foxminded.aprihodko.task10.dao.UserDao;
import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;

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
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldFindTeacherById() throws SQLException {
        User newTeacher = new Teacher("new Teacher", 104L);
        User firsetExpected = userDao.save(newTeacher);
        User firstActual = userDao.findById(5L).orElseThrow();
        newTeacher = new Teacher(5L, "rename", 103L);
        User secondExpected = userDao.save(newTeacher);
        User secondActual = userDao.findById(5L).orElseThrow();
        assertEquals(firsetExpected, firstActual);
        assertEquals(secondExpected, secondActual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldFindStudentById() throws SQLException {
        User expected = new Student(101L, "peter", 100L);
        User actual = userDao.findById(101L).orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldFindAllUsers() throws SQLException {
        List<User> expected = Arrays.asList(new Student(101L, "peter", 100L), new Student(103L, "bob", 101L),
                new User(104L, "none", UserType.NONE), new Teacher(102L, "alice", 101L),
                new Teacher(100L, "john", 100L));
        List<User> actual = userDao.findAll();
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shoudlDeleteTeacherById() throws SQLException {
        userDao.deleteById(100L);
        Optional<User> shouldBeEmpty = userDao.findById(100L);
        assertTrue(shouldBeEmpty.isEmpty());
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shoudlDeleteStudentById() throws SQLException {
        User expected = new Teacher("new Teacher", 104L);
        User actual = userDao.save(expected);
        User byId = userDao.findById(4L).orElseThrow();
        userDao.deleteById(4L);
        Optional<User> shouldBeEmpty = userDao.findById(5L);
        assertNotNull(actual.getId());
        expected.setId(actual.getId());
        assertEquals(expected, byId);
        assertEquals(expected, actual);
        assertTrue(shouldBeEmpty.isEmpty());
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shoudlFindTeaherByName() throws SQLException {
        User expected = new Teacher(100L, "john", 100L);
        User actual = userDao.findByName("john").orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shoudlFindStudentByName() throws SQLException {
        User expected = new Student(101L, "peter", 100L);
        User actual = userDao.findByName("peter").orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldFindTeachersByType() throws SQLException {
        List<User> expected = Arrays.asList(new Teacher(100L, "john", 100L), new Teacher(102L, "alice", 101L));
        List<User> actual = userDao.findByType(UserType.TEACHER);
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldFindStudentsByType() throws SQLException {
        List<User> expected = Arrays.asList(new Student(101L, "peter", 100L), new Student(103L, "bob", 101L));
        List<User> actual = userDao.findByType(UserType.STUDENT);
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldCreateTeacher() throws SQLException {
        User expected = new Teacher("new Teacher", 104L);
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
        User expected = new Student("john_new", 101L);
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
        expected = new Student(101L, "update_Stud", 102L);
        User actual = userDao.save(expected);
        assertNotNull(actual.getId());
        assertEquals(expected, actual);
    }

//    @Test
//    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
//    void shouldUpdateUser() throws SQLException {
//        User update = userDao.findById(104L).orElseThrow();
//        update = new User(104L, "new Teach", UserType.NONE);
//        User actual1 = userDao.save(update);
//        assertNotNull(actual1.getId());
//        assertEquals(update, actual1);
//    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldCreateUserNone() throws SQLException {
        User expected = new User("NONE", UserType.NONE);
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
        List<Teacher> expected = Arrays.asList(new Teacher(100L, "john", 100L));
        List<Teacher> actual = userDao.findTeacherByCourseId(100L);
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
    void shouldFindStudentByGroupId() throws SQLException {
        List<Student> expected = Arrays.asList(new Student(101L, "peter", 100L));
        List<Student> actual = userDao.findStudentByGroupId(100L);
        assertEquals(expected, actual);
    }
}