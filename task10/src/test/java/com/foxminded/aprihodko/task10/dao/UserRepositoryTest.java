package com.foxminded.aprihodko.task10.dao;

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
import com.foxminded.aprihodko.task10.repositories.UserRepository;
import com.foxminded.aprihodko.task10.models.Role;
import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest extends BaseDaoTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	void shouldFindTeacherById() throws SQLException {
		User expected = new Teacher(100L, "john", 100L, "12345678");
		User actual = userRepository.findById(100L).orElseThrow();
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	void shouldFindUserById() throws SQLException {
		User expected = new User(104L, "none", UserType.NONE, Role.ADMIN, "12345678");
		User actual = userRepository.findById(104L).orElseThrow();
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	void shouldFindStudentById() throws SQLException {
		User expected = new Student(101L, "peter", 100L, "12345678");
		User actual = userRepository.findById(101L).orElseThrow();
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	void shouldFindAllUsers() throws SQLException {
		List<User> expected = Arrays.asList(new Student(101L, "peter", 100L, "12345678"),
				new Student(103L, "bob", 101L, "12345678"), new User(104L, "none", UserType.NONE, Role.ADMIN, "12345678"),
				new Teacher(102L, "alice", 101L, "12345678"), new Teacher(100L, "john", 100L, "12345678"));
		List<User> actual = userRepository.findAll();
		assertEquals(expected.toString(), actual.toString());
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	@Transactional
	void shoudlDeleteTeacherById() throws SQLException {
		userRepository.deleteById(100L);
		Optional<User> shouldBeEmpty = userRepository.findById(100L);
		assertTrue(shouldBeEmpty.isEmpty());
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	@Transactional
	void shoudlDeleteStudentById() throws SQLException {
		userRepository.deleteById(101L);
		Optional<User> shouldBeEmpty = userRepository.findById(5L);
		assertTrue(shouldBeEmpty.isEmpty());
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	void shoudlFindTeaherByName() throws SQLException {
		User expected = new Teacher(100L, "john", 100L, "12345678");
		User actual = userRepository.findByName("john").orElseThrow();
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	void shoudlFindStudentByName() throws SQLException {
		User expected = new Student(101L, "peter", 100L, "12345678");
		User actual = userRepository.findByName("peter").orElseThrow();
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	void shouldFindTeachersByType() throws SQLException {
		List<User> expected = Arrays.asList(new Teacher(100L, "john", 100L, "12345678"),
				new Teacher(102L, "alice", 101L, "12345678"));
		List<User> actual = userRepository.findByType(UserType.TEACHER);
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	void shouldFindStudentsByType() throws SQLException {
		List<User> expected = Arrays.asList(new Student(101L, "peter", 100L, "12345678"),
				new Student(103L, "bob", 101L, "12345678"));
		List<User> actual = userRepository.findByType(UserType.STUDENT);
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	@Transactional
	void shouldCreateTeacher() throws SQLException {
		User expected = new Teacher("new Teacher", 104L, "12345678");
		User actual = userRepository.save(expected);
		User byId = userRepository.findById(2L).orElseThrow();
		assertNotNull(actual.getId());
		expected.setId(actual.getId());
		assertEquals(expected, actual);
		assertEquals(expected, byId);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	@Transactional
	void shouldCreateStudent() throws SQLException {
		User expected = new Student("john_new", 101L, "12345678");
		User actual = userRepository.save(expected);
		User byId = userRepository.findById(1L).orElseThrow();
		assertNotNull(actual.getId());
		expected.setId(actual.getId());
		assertEquals(expected, actual);
		assertEquals(expected, byId);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	@Transactional
	void shouldUpdateStudent() throws SQLException {
		User expected = userRepository.findById(101L).orElseThrow();
		expected = new Student(101L, "update_Stud", 100L, "12345678");
		User actual = userRepository.save(expected);
		assertNotNull(actual.getId());
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	@Transactional
	void shouldUpdateTeacher() throws SQLException {
		User update = userRepository.findById(100L).orElseThrow();
		update = new Teacher(100L, "new Teach", "12345678");
		User actual = userRepository.save(update);
		assertNotNull(actual.getId());
		assertEquals(update, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	@Transactional
	void shouldCreateUserNone() throws SQLException {
		User expected = new User("NONE", UserType.NONE, Role.ADMIN, "12345678");
		User actual = userRepository.save(expected);
		User byId = userRepository.findById(3L).orElseThrow();
		assertNotNull(actual.getId());
		expected.setId(actual.getId());
		assertEquals(expected, actual);
		assertEquals(expected, byId);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	void shouldFindTeachersByCourseId() throws SQLException {
		List<Teacher> expected = Arrays.asList(new Teacher(100L, "john", 100L, "12345678"));
		List<Teacher> actual = userRepository.findTeacherByCourseId(100L);
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	void shouldFindStudentByGroupId() throws SQLException {
		List<Student> expected = Arrays.asList(new Student(101L, "peter", 100L, "12345678"));
		List<Student> actual = userRepository.findStudentByGroupId(100L);
		assertEquals(expected, actual);
	}
}