package com.foxminded.aprihodko.task10.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.foxminded.aprihodko.task10.dao.UserDao;
import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;

@Repository
public abstract class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

//    @Override
//    public Optional<User> findById(Long id) throws SQLException {
//        User user = entityManager.find(User.class, id);
//        return user != null ? Optional.of(user) : Optional.empty();
//    }
//
//    @Override
//    public List<User> findAll() throws SQLException {
//        TypedQuery<User> query = entityManager.createQuery("Select u From User u", User.class);
//        return query.getResultList();
//    }
//
//    @Override
//    @Transactional
//    public void deleteById(Long id) throws SQLException {
//        try {
//            User user = entityManager.find(User.class, id);
//            entityManager.remove(user);
//        } catch (Exception e) {
//            logger.error("Unable to user user (id = " + id + ")");
//            throw new SQLException("Unable to delete user (id = " + id + ")" + e.getMessage());
//        }
//    }

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

//    @Override
//    @Transactional
//    public User create(User entity) throws SQLException {
//        try {
//            entityManager.persist(entity);
//            if (entity instanceof Student) {
//                Student student = (Student) entity;
//                entityManager.persist(student);
//                return new Student(student.getId(), student.getName(), student.getGroupId());
//            } else if (entity instanceof Teacher) {
//                Teacher teacher = (Teacher) entity;
//                entityManager.persist(teacher);
//                return new Teacher(teacher.getId(), teacher.getName(), teacher.getCourseId());
//            }
//        } catch (Exception e) {
//            logger.error("Unable to create User:{}", entity);
//            throw new SQLException("Unable to retrieve id " + entity.getId());
//        }
//        return new User(entity.getId(), entity.getName(), entity.getType(), entity.getRole(), entity.getPasswordHash());
//    }

//    @Override
//    @Transactional
//    public User update(User entity) throws SQLException {
//        try {
//            User user = findById(entity.getId()).orElseThrow();
//            entityManager.persist(user);
//            if (entity instanceof Student) {
//                Student student = (Student) entity;
//                return new Student(student.getId(), student.getName(), student.getGroupId());
//            } else if (entity instanceof Teacher) {
//                Teacher teacher = (Teacher) entity;
//                return new Teacher(teacher.getId(), teacher.getName(), teacher.getCourseId());
//            }
//        } catch (Exception e) {
//            logger.error("Unable to update User:{}", entity);
//            throw new SQLException("Unable to update user" + entity.getId());
//        }
//        return new User(entity.getId(), entity.getName(), entity.getType(), entity.getRole(), entity.getPasswordHash());
//    }

    @Override
    public List<Teacher> findTeacherByCourseId(Long courseId) throws SQLException {
        Query query = entityManager.createNativeQuery(
                "Select * from teachers AS t LEFT JOIN users AS u ON u.id = t.id WHERE t.course_id = " + courseId,
                Teacher.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findStudentByGroupId(Long groupId) throws SQLException {
        Query query = entityManager.createNativeQuery(
                "Select * from students AS s LEFT JOIN users AS u on u.id = s.id WHERE s.group_id = " + groupId,
                Student.class);
        return query.getResultList();
    }
}
