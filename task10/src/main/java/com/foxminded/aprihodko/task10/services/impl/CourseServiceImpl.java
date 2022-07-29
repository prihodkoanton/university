package com.foxminded.aprihodko.task10.services.impl;

import com.foxminded.aprihodko.task10.dao.CourseDao;
import com.foxminded.aprihodko.task10.models.Course;
import com.foxminded.aprihodko.task10.services.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Transactional
    public Optional<Course> findById(Long id) throws SQLException {
        return courseDao.findById(id);
    }

    @Transactional
    public List<Course> findAll() throws SQLException {
        return courseDao.findAll();
    }

    @Transactional
    public void deleteById(Long id) throws SQLException {
        courseDao.deleteById(id);
    }

    @Transactional
    public Optional<Course> findByName(String name) throws SQLException {
        return courseDao.findByName(name);
    }

    @Transactional
    public Course update(Course entity, Long id) throws SQLException {
        return courseDao.save(entity, id);
    }
}
