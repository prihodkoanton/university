package com.foxminded.aprihodko.task10.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.foxminded.aprihodko.task10.models.UserType;
import org.springframework.jdbc.core.JdbcTemplate;

import com.foxminded.aprihodko.task10.dao.AbstractCrudDao;
import com.foxminded.aprihodko.task10.dao.UserDao;
import com.foxminded.aprihodko.task10.dao.mapper.UserMapper;
import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.User;

public class UserDaoImpl extends AbstractCrudDao<User, Long> implements UserDao {

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
    public static final String CREATE_USER = "INSERT INTO university.users (user_id,  user_name, user_type) VALUES (?, ?, ?)";
    public static final String CREATE_STUDENT = "INSERT INTO university.students (user_ref, group_ref) VALUES (?, ?)";
    public static final String CREATE_TEACHER = "INSERT INTO university.teachers (user_ref, course_ref) VALUES (?, ?)";
    public static final String UPDATE = "UPDATE university.users SET user_name = ?, user_type = ? WHERE user_id = ?";
    public static final String UPDATE_GROUP_FOR_STUDENT = "UPDATE university.students SET group_ref = ? WHERE user_ref = ?";
    public static final String UPDATE_COURSE_FOR_TEACHER = "UPDATE university.teachers SET course_ref = ? WHERE user_ref = ?";
    public static final String FIND_STUDENT_BY_GROUP_ID = "select * from university.students s left join university.users u on s.user_ref = u.user_id   where group_ref = ?";
    public static final String FIND_TEACHER_BY_COURSE_ID = "select * from university.teachers t left join university.users u on t.user_ref = u.user_id   where course_ref = ?";

    private final JdbcTemplate jdbcTemplate;
    private final UserMapper mapper;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        mapper = new UserMapper();
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
        jdbcTemplate.update(DELETE_BY_ID, id);
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
    protected User create(User entity) throws SQLException {
        jdbcTemplate.update(CREATE_USER, entity.getId(), entity.getName(), entity.getType());
        if (entity instanceof Student) {
            Student student = (Student) entity;
            jdbcTemplate.update(CREATE_STUDENT, student.getId(), student.getGroupdId());
        } else if (entity instanceof Teacher) {
            Teacher teacher = (Teacher) entity;
            jdbcTemplate.update(CREATE_TEACHER, teacher.getId(), teacher.getCourseId());
        }
        return entity;
    }

    @Override
    protected User update(User entity, Long id) throws SQLException {
        jdbcTemplate.update(UPDATE, entity.getName(), entity.getType().toString(), id);
        if (entity instanceof Student) {
            Student student = (Student) entity;
            jdbcTemplate.update(UPDATE_GROUP_FOR_STUDENT, student.getId(), student.getGroupdId());
        } else if (entity.getType().equals(UserType.TEACHER)) {
            Teacher teacher = (Teacher) entity;
            jdbcTemplate.update(UPDATE_COURSE_FOR_TEACHER, teacher.getId(), teacher.getCourseId());
        }
        return entity;
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
