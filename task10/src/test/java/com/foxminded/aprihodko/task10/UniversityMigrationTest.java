package com.foxminded.aprihodko.task10;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class UniversityMigrationTest extends BaseDaoTest {

    public static final String QUERY_FOR_COURSES = "select column_name from information_schema.columns where table_name ='courses'";

    @Autowired
    JdbcTemplate template;

    @Test
    void shouldCreateTables() {

        List<String> strings = template.queryForList(QUERY_FOR_COURSES, String.class);

        List<String> expected = Arrays.asList("id", "course_name", "course_description");

        assertTrue(strings.containsAll(expected));
    }
}
