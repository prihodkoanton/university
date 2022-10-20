package com.foxminded.aprihodko.task10.repositories;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByName(String name);

	List<User> findByType(UserType userType);

	List<Teacher> findTeacherByCourseId(Long id) throws SQLException;

	List<Student> findStudentByGroupId(Long id) throws SQLException;
}
