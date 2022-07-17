package com.foxminded.aprihodko.task10.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.foxminded.aprihodko.task10.models.Student;

public interface StudentDao extends CrudDao<Student, Long> {
    List<Student> findStudentByGroupId(String name) throws SQLException;
}
