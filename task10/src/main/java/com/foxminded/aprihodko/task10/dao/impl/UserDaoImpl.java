package com.foxminded.aprihodko.task10.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.dao.AbstractCrudDao;
import com.foxminded.aprihodko.task10.dao.UserDao;
import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;

@Repository
public class UserDaoImpl extends AbstractCrudDao<User, Long> implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

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
    public static final String CREATE_USER = "INSERT INTO university.users(user_name, user_type, user_role, user_password) VALUES  (?, ?, ?, ?)";
    public static final String CREATE_STUDENT = "INSERT INTO university.students(group_ref) VALUES (?)";
    public static final String CREATE_TEACHER = "INSERT INTO university.teachers(course_ref) VALUES (?)";
    public static final String UPDATE = "UPDATE university.users SET user_name = ?, user_type = ?, user_role = ?, user_password = ?  WHERE user_id = ?";
    public static final String UPDATE_GROUP_FOR_STUDENT = "UPDATE university.students SET group_ref = ? WHERE user_ref = ?";
    public static final String UPDATE_COURSE_FOR_TEACHER = "UPDATE university.teachers SET course_ref = ? WHERE user_ref = ?";
    public static final String FIND_STUDENT_BY_GROUP_ID = "select * from university.students s left join university.users u on s.user_ref = u.user_id   where group_ref = ?";
    public static final String FIND_TEACHER_BY_COURSE_ID = "select * from university.teachers t left join university.users u on t.user_ref = u.user_id   where course_ref = ?";

    @Override
    public Optional<User> findById(Long id) throws SQLException {
//        TypedQuery<User> query = entityManager.createQuery(
//                "Select u from User u LEFT JOIN u.university.students s LEFT JOIN u.university.teachers t WHERE u.id = "
//                        + id,
//                User.class);
        User user = (User) entityManager.createNativeQuery(
                "select * from university.users u left join university.students s on u.id = s.id left join university.teachers t on u.id = t.id where u.id = "
                        + id,
                User.class).getSingleResult();
        if (user instanceof Student) {
            Student student = (Student) user;
            return student != null ? Optional.of(student) : Optional.empty();
        } else if (user instanceof Teacher) {
            Teacher teacher = (Teacher) user;
            return teacher != null ? Optional.of(teacher) : Optional.empty();
        }
        return user != null ? Optional.of(user) : Optional.empty();
    }

    @Override
    public List<User> findAll() throws SQLException {
        TypedQuery<User> query = entityManager.createQuery("Select u From User u", User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws SQLException {
        try {
            User user = findById(id).orElseThrow();
            entityManager.remove(user);
        } catch (Exception e) {
            logger.error("Unable to user user (id = " + id + ")");
            throw new SQLException("Unable to delete user (id = " + id + ")" + e.getMessage());
        }
    }

    @Override
    public Optional<User> findByName(String name) {
        TypedQuery<User> query = entityManager.createQuery("From User WHERE user_name = '" + name + "'", User.class);
        User user = query.getSingleResult();
        return user != null ? Optional.of(user) : Optional.empty();
    }

    @Override
    public List<User> findByType(UserType userType) {
        TypedQuery<User> query = entityManager.createQuery("From User WHERE user_type = '" + userType.toString() + "'",
                User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public User create(User entity) throws SQLException {
        try {
            entityManager.persist(entity);
            if (entity instanceof Student) {
                Student student = (Student) entity;
                return new Student(student.getId(), student.getName(), student.getGroupId());
            } else if (entity instanceof Teacher) {
                Teacher teacher = (Teacher) entity;
                return new Teacher(teacher.getId(), teacher.getName(), teacher.getCourseId());
            }
        } catch (Exception e) {
            logger.error("Unable to create User:{}", entity);
            throw new SQLException("Unable to retrieve id" + entity.getId());
        }
        return new User(entity.getId(), entity.getName(), entity.getType(), entity.getRole(), entity.getPasswordHash());
    }

    @Override
    @Transactional
    public User update(User entity) throws SQLException {
        try {
            User user = findById(entity.getId()).orElseThrow();
            entityManager.merge(user);
            if (entity instanceof Student) {
                Student student = (Student) entity;
//            logger.error("Unable to update Student:{}", student);
//            throw new SQLException("Unable to update Student", student.toString());\
                return new Student(student.getId(), student.getName(), student.getGroupId());
            } else if (entity instanceof Teacher) {
                Teacher teacher = (Teacher) entity;
//                logger.error("Unable to update Teacher:{}", teacher);
//                throw new SQLException("Unable to update Teacher", teacher.toString());
                return new Teacher(teacher.getId(), teacher.getName(), teacher.getCourseId());
            }
        } catch (Exception e) {
            logger.error("Unable to update User:{}", entity);
            throw new SQLException("Unable to update user" + entity.getId());
        }
        return new User(entity.getId(), entity.getName(), entity.getType(), entity.getRole(), entity.getPasswordHash());
    }

    @Override
    public List<Teacher> findTeacherByCourseId(Long courseId) throws SQLException {
//
        TypedQuery<Teacher> query = entityManager.createQuery(
                "Select t from Teacher t LEFT JOIN t.university.users u WHERE u.course_ref = " + courseId,
                Teacher.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findStudentByGroupId(Long groupId) throws SQLException {
        TypedQuery<Student> query = entityManager.createQuery(
                "Select s from Student s LEFT JOIN s.university.users u WHERE u.group_ref = " + groupId, Student.class);
        return query.getResultList();
    }
}
