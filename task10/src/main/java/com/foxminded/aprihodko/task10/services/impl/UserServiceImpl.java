package com.foxminded.aprihodko.task10.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.dao.UserDao;
import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;
import com.foxminded.aprihodko.task10.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public Optional<User> findById(Long id) throws SQLException {
        return userDao.findById(id);
    }

    @Transactional
    public List<User> findAll() throws SQLException {
        return userDao.findAll();
    }

    @Transactional
    public void deleteById(Long id) throws SQLException {
        userDao.deleteById(id);
    }

    @Transactional
    public Optional<User> findByName(String name) {
        return userDao.findByName(name);
    }

    @Transactional
    public List<User> findByType(UserType userType) {
        return userDao.findByType(userType);
    }
    
    

    @Transactional
    public User update(User entity, Long id) throws SQLException {
        return userDao.save(entity, id);
    }
    
    @Transactional
    public List<Student> findStudentByGroupId(Long id)  throws SQLException {
        return userDao.findStudentByGroupId(id);
    }
    
    @Transactional
    public List<Teacher> findTeacherByCourseId(Long id)  throws SQLException {
        return userDao.findTeacherByCourseId(id);
    }
}
