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

import com.foxminded.aprihodko.task10.dao.LessonDao;
import com.foxminded.aprihodko.task10.models.Lesson;

@Repository
public abstract class LessonDaoImpl implements LessonDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(LessonDaoImpl.class);

//    @Override
//    public Optional<Lesson> findById(Long id) throws SQLException {
//        Lesson lesson = entityManager.find(Lesson.class, id);
//        return lesson != null ? Optional.of(lesson) : Optional.empty();
//    }
//
//    @Override
//    public List<Lesson> findAll() throws SQLException {
//        TypedQuery<Lesson> query = entityManager.createQuery("SELECT l FROM Lesson l", Lesson.class);
//        return query.getResultList();
//    }
//
//    @Override
//    @Transactional
//    public void deleteById(Long id) throws SQLException {
//        try {
//            Lesson lesson = findById(id).orElseThrow();
//            entityManager.remove(lesson);
//        } catch (Exception e) {
//            logger.error("Unable to user lesson (id = " + id + ")");
//            throw new SQLException("Unable to delete lesson (id = " + id + ")");
//        }
//    }

    @Override
    public Optional<Lesson> findByDayOfWeek(String dayOfWeek) throws SQLException {
        TypedQuery<Lesson> query = entityManager
                .createQuery("FROM Lesson WHERE lesson_day_of_week = '" + dayOfWeek + "'", Lesson.class);
        Lesson lesson = query.getSingleResult();
        return lesson != null ? Optional.of(lesson) : Optional.empty();
    }

    @Override
    public List<Lesson> findByRoomId(Long id) throws SQLException {
        TypedQuery<Lesson> query = entityManager.createQuery("FROM Lesson WHERE room_ref = " + id, Lesson.class);
        return query.getResultList();
    }

    @Override
    public List<Lesson> findByGroupId(Long id) throws SQLException {
        TypedQuery<Lesson> query = entityManager.createQuery("FROM Lesson WHERE group_ref = " + id, Lesson.class);
        return query.getResultList();
    }

    @Override
    public List<Lesson> findByCourseId(Long id) throws SQLException {
        TypedQuery<Lesson> query = entityManager.createQuery("FROM Lesson WHERE course_ref = " + id, Lesson.class);
        return query.getResultList();
    }

    @Override
    public List<Lesson> findByTeacherId(Long id) throws SQLException {
        TypedQuery<Lesson> query = entityManager.createQuery("FROM Lesson WHERE teacher_ref = " + id, Lesson.class);
        return query.getResultList();
    }

    @Override
    public List<Lesson> findByTimeSpan(int timeSpan) throws SQLException {
        TypedQuery<Lesson> query = entityManager.createQuery("FROM Lesson WHERE lesson_time_span = " + timeSpan,
                Lesson.class);
        return query.getResultList();
    }

//    @Override
//    @Transactional
//    public Lesson create(Lesson entity) throws SQLException {
//        entityManager.persist(entity);
//        return new Lesson(entity.getId(), entity.getDayOfWeek(), entity.getTimeSpan(), entity.getRoomId(),
//                entity.getGroupId(), entity.getCourseId(), entity.getTeacherId());
//    }
//
//    @Override
//    @Transactional
//    public Lesson update(Lesson entity) throws SQLException {
//        Lesson lesson = findById(entity.getId()).orElseThrow();
//        entityManager.merge(lesson);
//        return new Lesson(entity.getId(), entity.getDayOfWeek(), entity.getTimeSpan(), entity.getRoomId(),
//                entity.getGroupId(), entity.getCourseId(), entity.getTeacherId());
//    }
}
