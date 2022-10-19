package com.foxminded.aprihodko.task10.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.models.Course;
import com.foxminded.aprihodko.task10.repositories.CourseRepository;
import com.foxminded.aprihodko.task10.services.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;

	public CourseServiceImpl(CourseRepository courseDao) {
		this.courseRepository = courseDao;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Course> findById(Long id) throws SQLException {
		return courseRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> findAll() throws SQLException {
		return courseRepository.findAll();
	}

	@Override
	@Transactional
	public void deleteById(Long id) throws SQLException {
		courseRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Course> findByName(String name) throws SQLException {
		return courseRepository.findByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Course> findByDescription(String descripteon) throws SQLException {
		return courseRepository.findByDescription(descripteon);
	}

	@Override
	@Transactional
	public Course save(Course entity) throws SQLException {
		return courseRepository.save(entity);
	}
}
