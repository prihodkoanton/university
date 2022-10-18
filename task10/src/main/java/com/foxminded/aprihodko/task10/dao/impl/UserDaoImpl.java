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
