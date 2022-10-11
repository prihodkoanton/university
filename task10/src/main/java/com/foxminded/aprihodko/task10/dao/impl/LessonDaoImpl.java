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

import com.foxminded.aprihodko.task10.dao.AbstractCrudDao;
import com.foxminded.aprihodko.task10.dao.LessonDao;
import com.foxminded.aprihodko.task10.models.Lesson;

@Repository
public class LessonDaoImpl extends AbstractCrudDao<Lesson, Long> implements LessonDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(LessonDaoImpl.class);

    public static final String FIND_BY_ID = "select * from university.lessons l left join university.rooms r on l.room_ref = r.room_id\n"
            + "                                    left join university.groups g on l.group_ref = g.group_id\n"
            + "                                    left join university.courses c on l.course_ref = c.course_id \n"
            + "                                    left join university.teachers t on l.teacher_ref = t.user_ref \n"
            + "                                    where l.lesson_id = ?";
    public static final String FIND_ALL = "SELECT * FROM university.lessons";
    public static final String DELETE_BY_ID = "DELETE FROM university.lessons WHERE lesson_id = ?";
    public static final String FIND_BY_DAY_OF_WEEK = "SELECT * FROM university.lessons WHERE lesson_day_of_weak = ?";
    public static final String FIND_BY_ROOM_ID = "SELECT * FROM university.lessons WHERE room_ref = ?";
    public static final String FIND_BY_GROUP_ID = "SELECT * FROM university.lessons WHERE group_ref = ?";
    public static final String FIND_BY_COURSE_ID = "SELECT * FROM university.lessons WHERE course_ref = ?";
    public static final String FIND_BY_TEACH_ID = "SELECT * FROM university.lessons WHERE teacher_ref = ?";
    public static final String FIND_BY_TIME_SPAN = "SELECT * FROM university.lessons WHERE lesson_time_span = ?";
    public static final String CREATE = "INSERT INTO university.lessons (lesson_day_of_week, lesson_time_span, room_ref, group_ref, course_ref, teacher_ref) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE university.lessons SET lesson_day_of_week =?, lesson_time_span =?, room_ref =?, group_ref =?, course_ref =?, teacher_ref =? WHERE lesson_id = ?";

    @Override
    public Optional<Lesson> findById(Long id) throws SQLException {
        Lesson lesson = entityManager.find(Lesson.class, id);
        return lesson != null ? Optional.of(lesson) : Optional.empty();
    }

    @Override
    public List<Lesson> findAll() throws SQLException {
        TypedQuery<Lesson> query = entityManager.createQuery("SELECT l FROM Lesson l", Lesson.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        Lesson lesson = findById(id).orElseThrow();
        entityManager.remove(lesson);
    }

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
    public List<Lesson> findByTimeSpan(Long timeSpan) throws SQLException {
        TypedQuery<Lesson> query = entityManager.createQuery("FROM Lesson WHERE lesson_time_span = " + timeSpan,
                Lesson.class);
        return query.getResultList();
    }

    @Override
    public Lesson create(Lesson entity) throws SQLException {
        entityManager.persist(entity);
        return new Lesson(entity.getId(), entity.getDayOfWeek(), entity.getTimeSpan(), entity.getRoomId(),
                entity.getGroupId(), entity.getCourseId(), entity.getTeacherId());
    }

    @Override
    public Lesson update(Lesson entity) throws SQLException {
        Lesson lesson = findById(entity.getId()).orElseThrow();
        entityManager.merge(lesson);
        return new Lesson(entity.getId(), entity.getDayOfWeek(), entity.getTimeSpan(), entity.getRoomId(),
                entity.getGroupId(), entity.getCourseId(), entity.getTeacherId());
    }
}
