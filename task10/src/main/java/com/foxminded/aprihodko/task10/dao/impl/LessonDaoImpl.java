package com.foxminded.aprihodko.task10.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.foxminded.aprihodko.task10.dao.AbstractCrudDao;
import com.foxminded.aprihodko.task10.dao.LessonDao;
import com.foxminded.aprihodko.task10.dao.mapper.LessonMapper;
import com.foxminded.aprihodko.task10.models.Lesson;

@Repository
public class LessonDaoImpl extends AbstractCrudDao<Lesson, Long> implements LessonDao {

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

    private final LessonMapper mapper;
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public LessonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        mapper = new LessonMapper();
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName("university.lessons")
                .usingColumns("lesson_day_of_week", "lesson_time_span", "room_ref", "group_ref", "course_ref",
                        "teacher_ref")
                .usingGeneratedKeyColumns("lesson_id");
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
        int deleteRowCount = jdbcTemplate.update(DELETE_BY_ID, id);
        if (deleteRowCount != 1) {
            logger.error("Unable to delete lesson (id = " + id + ")");
            throw new SQLException("Unable to delete course (id = " + id + ")");
        }
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
    public Lesson create(Lesson entity) throws SQLException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("lesson_day_of_week", entity.getDayOfWeek().toString());
        parameters.put("lesson_time_span", entity.getTimeSpan());
        parameters.put("room_ref", entity.getRoomId());
        parameters.put("group_ref", entity.getGroupId());
        parameters.put("course_ref", entity.getCourseId());
        parameters.put("teacher_ref", entity.getTeacherId());
        Number id = simpleJdbcInsert.executeAndReturnKey(parameters);
        if (id == null) {
            logger.error("Unable to create Lesson:{}", entity);
            throw new SQLException("Unable to retrieve id" + entity.getId());
        }
        return new Lesson(id.longValue(), entity.getDayOfWeek(), entity.getTimeSpan(), entity.getRoomId(),
                entity.getGroupId(), entity.getCourseId(), entity.getTeacherId());
    }

    @Override
    public Lesson update(Lesson entity) throws SQLException {
        int updatedRowCount = jdbcTemplate.update(UPDATE, entity.getDayOfWeek().toString(), entity.getTimeSpan(),
                entity.getRoomId(), entity.getGroupId(), entity.getCourseId(), entity.getTeacherId(), entity.getId());
        if (updatedRowCount != 1) {
            logger.error("Unable to update Lesson:{}", entity);
            throw new SQLException("Unable to update lesson" + entity.getId());
        }
        return new Lesson(entity.getId(), entity.getDayOfWeek(), entity.getTimeSpan(), entity.getRoomId(),
                entity.getGroupId(), entity.getCourseId(), entity.getTeacherId());
    }
}
