package com.foxminded.aprihodko.task10.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxminded.aprihodko.task10.dao.AbstractCrudDao;
import com.foxminded.aprihodko.task10.dao.LessonDao;
import com.foxminded.aprihodko.task10.dao.mapper.LessonMapper;
import com.foxminded.aprihodko.task10.models.Lesson;

@Repository
public class LessonDaoImpl extends AbstractCrudDao<Lesson, Long> implements LessonDao {

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
    public static final String CREATE = "INSET INTO university.lessons (lesson_id, lesson_day_of_week, lesson_time_span, room_ref, group_ref, course_ref, teacher_ref) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE university.lessons SET lesson_day_of_week =?, lesson_time_span =?, room_ref =?, group_ref =?, course_ref =?, teacher_ref =? WHERE lesson_id = ?";

    private final LessonMapper mapper;
    private final JdbcTemplate jdbcTemplate;

    public LessonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        mapper = new LessonMapper();
    }

    @Override
    public Optional<Lesson> findById(Long id) throws SQLException {
        return jdbcTemplate.query(FIND_BY_ID, mapper, id).stream().findFirst();
    }

    @Override
    public List<Lesson> findAll() throws SQLException {
        return jdbcTemplate.query(FIND_ALL, mapper);
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public Optional<Lesson> findByDayOfWeek(String dayOfWeek) throws SQLException {
        return jdbcTemplate.query(FIND_BY_DAY_OF_WEEK, mapper, dayOfWeek).stream().findFirst();
    }

    @Override
    public List<Lesson> findByRoomId(Long id) throws SQLException {
        return jdbcTemplate.query(FIND_BY_ROOM_ID, mapper, id);
    }

    @Override
    public List<Lesson> findByGroupId(Long id) throws SQLException {
        return jdbcTemplate.query(FIND_BY_GROUP_ID, mapper, id);
    }

    @Override
    public List<Lesson> findByCourseId(Long id) throws SQLException {
        return jdbcTemplate.query(FIND_BY_COURSE_ID, mapper, id);
    }

    @Override
    public List<Lesson> findByTeacherId(Long id) throws SQLException {
        return jdbcTemplate.query(FIND_BY_TEACH_ID, mapper, id);
    }

    @Override
    public List<Lesson> findByTimeSpan(Long timeSpan) throws SQLException {
        return jdbcTemplate.query(FIND_BY_TIME_SPAN, mapper, timeSpan);
    }

    @Override
    protected Lesson create(Lesson entity) throws SQLException {
        jdbcTemplate.update(CREATE, entity.getId(), entity.getDayOfWeek(), entity.getTimeSpan(), entity.getRoomId(),
                entity.getGroupId(), entity.getCourseId(), entity.getTeacherId());
        return entity;
    }

    @Override
    protected Lesson update(Lesson entity, Long id) throws SQLException {
        jdbcTemplate.update(UPDATE, entity.getDayOfWeek().toString(), entity.getTimeSpan(), entity.getRoomId(),
                entity.getGroupId(), entity.getCourseId(), entity.getTeacherId(), id);
        return entity;
    }
}
