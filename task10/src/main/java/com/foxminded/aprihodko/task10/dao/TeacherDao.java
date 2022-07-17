package com.foxminded.aprihodko.task10.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.foxminded.aprihodko.task10.models.Teacher;

public interface TeacherDao extends CrudDao<Teacher, Long> {
    List<Teacher> findByCourseId(Long id) throws SQLException;
}
