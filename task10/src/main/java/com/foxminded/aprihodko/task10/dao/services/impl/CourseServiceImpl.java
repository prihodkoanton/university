package com.foxminded.aprihodko.task10.dao.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.dao.impl.CourseDaoImpl;
import com.foxminded.aprihodko.task10.dao.services.CourseService;
import com.foxminded.aprihodko.task10.models.Course;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDaoImpl courseDaoImpl;
    
    public CourseServiceImpl(CourseDaoImpl courseDaoImpl) {
        this.courseDaoImpl = courseDaoImpl;
    }

    @Transactional
    Optional<Course> findById(Long id) throws SQLException {
        return courseDaoImpl.findById(id);
    }

    @Transactional
    List<Course> findAll() throws SQLException {
        return courseDaoImpl.findAll();
    }
    
    @Transactional
    void deleteById(Long id) throws SQLException {
        courseDaoImpl.deleteById(id);
    }
    
    @Transactional
    Optional<Course> findByName(String name) throws SQLException {
        return courseDaoImpl.findByName(name);
    }
    
    @Transactional
    Course update(Course entity, Long id) throws SQLException {
        return courseDaoImpl.save(entity, id);
    }
}
