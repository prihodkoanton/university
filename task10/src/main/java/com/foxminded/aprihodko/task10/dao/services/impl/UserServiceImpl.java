package com.foxminded.aprihodko.task10.dao.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.dao.impl.UserDaoImpl;
import com.foxminded.aprihodko.task10.dao.services.UserService;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;

@Service
public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDaoImpl;

    public UserServiceImpl(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    @Transactional
    Optional<User> findById(Long id) throws SQLException {
        return userDaoImpl.findById(id);
    }
    
    @Transactional
    List<User> findAll() throws SQLException {
        return userDaoImpl.findAll();
    }
   
    @Transactional
    void deleteById(Long id) throws SQLException {
        userDaoImpl.deleteById(id);
    }
    
    @Transactional
    Optional<User> findByName(String name) {
        return userDaoImpl.findByName(name);
    }
    
    @Transactional
    List<User> findByType(UserType userType) {
        return userDaoImpl.findByType(userType);
    }
    
    @Transactional
    User update(User entity, Long id) throws SQLException {
        return userDaoImpl.save(entity, id);
    }
}
