package com.foxminded.aprihodko.task10.dao.impl;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.BaseDaoTest;
import com.foxminded.aprihodko.task10.dao.GroupDao;
import com.foxminded.aprihodko.task10.models.Group;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GroupDaoImplTest extends BaseDaoTest {

    @Autowired
    private GroupDao groupDao;

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/group_test_data.sql" })
    void shouldFindById() throws SQLException {
        Group expected = new Group(100L, "group for Java");
        Group actual = groupDao.findById(100L).orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/group_test_data.sql" })
    void shouldFindAll() throws SQLException {
        List<Group> expected = Arrays.asList(new Group(100L, "group for Java"),
                new Group(101L, "group for Java Spring"));
        List<Group> actual = groupDao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/group_test_data.sql" })
    void shouldNotDeleteById() throws SQLException {
        Exception e = assertThrows(NoSuchElementException.class, () -> groupDao.deleteById(10L));
        assertEquals("No value present", e.getMessage());
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/group_test_data.sql" })
    void shouldDeleteById() throws SQLException {
        groupDao.deleteById(100L);
        Optional<Group> shouldBeEmpty = groupDao.findById(100L);
        assertTrue(shouldBeEmpty.isEmpty());
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/group_test_data.sql" })
    void shouldFindByName() throws SQLException {
        Group expected = new Group(100L, "group for Java");
        Group actual = groupDao.findByName("group for Java").orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/group_test_data.sql" })
    @Transactional
    void shouldCreateGroup() throws SQLException {
        Group expected = new Group("group for Java");
        Group actual = groupDao.save(expected);
        assertNotNull(actual.getId());
        expected.setId(actual.getId());
        assertEquals(expected, actual);
    }
}
