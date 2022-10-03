package com.foxminded.aprihodko.task10.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.dao.UserDao;
import com.foxminded.aprihodko.task10.models.MyUserPrincipal;
import com.foxminded.aprihodko.task10.models.Role;
import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;
import com.foxminded.aprihodko.task10.services.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
        return MyUserPrincipal.fromUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) throws SQLException {
        return userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() throws SQLException {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws SQLException {
        userDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findByType(UserType userType) {
        return userDao.findByType(userType);
    }

    @Override
    @Transactional
    public User save(User entity) throws SQLException {
        if (entity instanceof User) {
            entity.setRole(Role.ADMIN);
        } else {
            entity.setRole(Role.USER);
        }
        return userDao.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findStudentByGroupId(Long id) throws SQLException {
        return userDao.findStudentByGroupId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> findTeacherByCourseId(Long id) throws SQLException {
        return userDao.findTeacherByCourseId(id);
    }
}
