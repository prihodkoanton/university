package com.foxminded.aprihodko.task10.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.foxminded.aprihodko.task10.dao.AbstractCrudDao;
import com.foxminded.aprihodko.task10.dao.UserDao;
import com.foxminded.aprihodko.task10.dao.mapper.UserMapper;
import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;

@Repository
public class UserDaoImpl extends AbstractCrudDao<User, Long> implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public static final String FIND_BY_ID = "select * from university.users u left join university.students s on u.user_id = s.user_ref left join university.teachers t on u.user_id = t.user_ref where u.user_id = ?";
    public static final String FIND_ALL = "" + "select *\n" + "from university.users u\n"
            + "         left join university.students s on u.user_id = s.user_ref\n"
            + "         left join university.teachers t on u.user_id = t.user_ref;";
    public static final String DELETE_BY_ID = "DELETE FROM university.users WHERE user_id = ?";
    public static final String FIND_BY_NAME = "select *\n" + "from university.users u\n"
            + "         left join university.students s on u.user_id = s.user_ref\n"
            + "         left join university.teachers t on u.user_id = t.user_ref\n" + "where user_name = ?;";
    public static final String FIND_BY_USER_TYPE = "" + "select *\n" + "from university.users u\n"
            + "         left join university.students s on u.user_id = s.user_ref\n"
            + "         left join university.teachers t on u.user_id = t.user_ref\n" + "where user_type = ?;";
    public static final String CREATE_USER = "INSERT INTO university.users(user_name, user_type) VALUES  (?, ?)";
    public static final String CREATE_STUDENT = "INSERT INTO university.students(group_ref) VALUES (?)";
    public static final String CREATE_TEACHER = "INSERT INTO university.teachers(course_ref) VALUES (?)";
    public static final String UPDATE = "UPDATE university.users SET user_name = ?, user_type = ? WHERE user_id = ?";
    public static final String UPDATE_GROUP_FOR_STUDENT = "UPDATE university.students SET group_ref = ? WHERE user_ref = ?";
    public static final String UPDATE_COURSE_FOR_TEACHER = "UPDATE university.teachers SET course_ref = ? WHERE user_ref = ?";
    public static final String FIND_STUDENT_BY_GROUP_ID = "select * from university.students s left join university.users u on s.user_ref = u.user_id   where group_ref = ?";
    public static final String FIND_TEACHER_BY_COURSE_ID = "select * from university.teachers t left join university.users u on t.user_ref = u.user_id   where course_ref = ?";

    private final JdbcTemplate jdbcTemplate;
    private final UserMapper mapper;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        mapper = new UserMapper();
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName("university.users")
                .usingColumns("user_name", "user_type").usingGeneratedKeyColumns("user_id");
    }

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        return jdbcTemplate.query(FIND_BY_ID, mapper, id).stream().findFirst();
    }

    @Override
    public List<User> findAll() throws SQLException {
        return jdbcTemplate.query(FIND_ALL, mapper);
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        int deleteRowCount = jdbcTemplate.update(DELETE_BY_ID, id);
        if (deleteRowCount != 1) {
            logger.error("Unable to user group (id = " + id + ")");
            throw new SQLException("Unable to delete course (id = " + id + ")");
        }
    }

    @Override
    public Optional<User> findByName(String name) {
        return jdbcTemplate.query(FIND_BY_NAME, mapper, name).stream().findFirst();
    }

    @Override
    public List<User> findByType(UserType userType) {
        return jdbcTemplate.query(FIND_BY_USER_TYPE, mapper, userType.name());
    }

    @Override
    public User create(User entity) throws SQLException {
        Map<String, Object> usersParameters = new HashMap<String, Object>();
        usersParameters.put("user_name", entity.getName());
        usersParameters.put("user_type", entity.getType().toString());
        Number id = simpleJdbcInsert.executeAndReturnKey(usersParameters);
        if (id == null) {
            logger.error("Unable to create User:{}", entity);
            throw new SQLException("Unable to retrieve id" + entity.getId());
        }
        if (entity instanceof Student) {
            Student student = (Student) entity;
            Map<String, Object> studentParameters = new HashMap<String, Object>();
            usersParameters.put("group_ref", student.getGroupdId());
            Number idStudent = simpleJdbcInsert.executeAndReturnKey(studentParameters);
            if (idStudent == null) {
                logger.error("Unable to create Student:{}", student);
                throw new SQLException("Unable to create Teacher", student.toString());
            }
            return new Student(idStudent.longValue(), student.getName(), student.getGroupdId());
        } else if (entity instanceof Teacher) {
            Teacher teacher = (Teacher) entity;
            Map<String, Object> teacherParameters = new HashMap<String, Object>();
            usersParameters.put("course_ref", teacher.getCourseId());
            Number idTeacher = simpleJdbcInsert.executeAndReturnKey(teacherParameters);
            if (idTeacher == null) {
                logger.error("Unable to create Teacher:{}", teacher);
                throw new SQLException("Unable to create Teacher", teacher.toString());
            }
            return new Teacher(idTeacher.longValue(), teacher.getName(), teacher.getCourseId());
        }
        return new User(id.longValue(), entity.getName(), entity.getType());
    }

    @Override
    public User update(User entity) throws SQLException {
        int updatedUserRowCount = jdbcTemplate.update(UPDATE, entity.getName(), entity.getType().toString(),
                entity.getId());
        if (updatedUserRowCount != 1) {
            logger.error("Unable to update User:{}", entity);
            throw new SQLException("Unable to update user" + entity.getId());
        }
        if (entity instanceof Student) {
            Student student = (Student) entity;
            int updatedStudentRowCount = jdbcTemplate.update(UPDATE_GROUP_FOR_STUDENT, student.getGroupdId(),
                    student.getId());
            if (updatedStudentRowCount != 1) {
                logger.error("Unable to update Student:{}", student);
                throw new SQLException("Unable to update Student", student.toString());
            }
            return new Student(student.getId(), student.getName());
        } else if (entity instanceof Teacher) {
            Teacher teacher = (Teacher) entity;
            int updatedTeacherRowCount = jdbcTemplate.update(UPDATE_COURSE_FOR_TEACHER, teacher.getCourseId(),
                    teacher.getId());
            if (updatedTeacherRowCount != 1) {
                logger.error("Unable to update Teacher:{}", teacher);
                throw new SQLException("Unable to update Teacher", teacher.toString());
            }
            return new Teacher(entity.getId(), entity.getName());
        }
        return new User(entity.getId(), entity.getName(), entity.getType());
    }

    @Override
    public List<Teacher> findTeacherByCourseId(Long courseId) throws SQLException {
        return jdbcTemplate.query(FIND_TEACHER_BY_COURSE_ID, mapper, courseId).stream().map(u -> (Teacher) u)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentByGroupId(Long courseId) throws SQLException {
        return jdbcTemplate.query(FIND_STUDENT_BY_GROUP_ID, mapper, courseId).stream().map(u -> (Student) u)
                .collect(Collectors.toList());
    }
}
