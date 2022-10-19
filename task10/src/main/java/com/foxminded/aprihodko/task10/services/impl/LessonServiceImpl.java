package com.foxminded.aprihodko.task10.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.models.Lesson;
import com.foxminded.aprihodko.task10.repositories.LessonRepository;
import com.foxminded.aprihodko.task10.services.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

	private final LessonRepository lessonRepository;

	public LessonServiceImpl(LessonRepository lessonRepository) {
		this.lessonRepository = lessonRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Lesson> findById(Long id) throws SQLException {
		return lessonRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lesson> findAll() throws SQLException {
		return lessonRepository.findAll();
	}

	@Override
	@Transactional
	public void deleteById(Long id) throws SQLException {
		lessonRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Lesson> findByDayOfWeek(String dayOfWeek) throws SQLException {
		return lessonRepository.findByDayOfWeek(dayOfWeek);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lesson> findByRoomId(Long id) throws SQLException {
		return lessonRepository.findByRoomId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lesson> findByGroupId(Long id) throws SQLException {
		return lessonRepository.findByGroupId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lesson> findByCourseId(Long id) throws SQLException {
		return lessonRepository.findByCourseId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lesson> findByTeacherId(Long id) throws SQLException {
		return lessonRepository.findByTeacherId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lesson> findByTimeSpan(int timeSpan) throws SQLException {
		return lessonRepository.findByTimeSpan(timeSpan);
	}

	@Override
	@Transactional
	public Lesson save(Lesson entity) throws SQLException {
		return lessonRepository.save(entity);
	}
}
