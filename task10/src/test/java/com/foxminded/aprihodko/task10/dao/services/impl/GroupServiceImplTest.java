package com.foxminded.aprihodko.task10.dao.services.impl;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;

import com.foxminded.aprihodko.task10.BaseDaoTest;
import com.foxminded.aprihodko.task10.dao.GroupDao;
import com.foxminded.aprihodko.task10.models.Group;
import com.foxminded.aprihodko.task10.services.GroupService;
import com.foxminded.aprihodko.task10.services.impl.GroupServiceImpl;

@SpringBootTest(classes = { GroupServiceImpl.class })
class GroupServiceImplTest extends BaseDaoTest {

    @MockBean
    GroupDao groupDao;

    @Autowired
    private GroupService groupServiceImpl;

    @Test
    void shouldFindById() throws SQLException {
        Group group = new Group(100L, "test");
        when(groupDao.findById(100L)).thenReturn(Optional.of(group));
        Optional<Group> expected = groupDao.findById(100L);
        Optional<Group> actual = groupServiceImpl.findById(100L);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAll() throws SQLException {
        List<Group> groups = Arrays.asList(new Group(100L, "group for Java"), new Group(101L, "group for Java Spring"));
        when(groupDao.findAll()).thenReturn(groups);
        List<Group> expected = groupDao.findAll();
        List<Group> actual = groupServiceImpl.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteById() throws SQLException {
        groupServiceImpl.deleteById(100L);
        verify(groupDao).deleteById(100L);
    }

    @Test
    void shouldNotDeleteById() throws SQLException {
        when(groupDao.findById(10L)).thenReturn(null);
        doThrow(new EmptyResultDataAccessException(1)).when(groupDao).deleteById(10L);
        Exception actual = assertThrows(EmptyResultDataAccessException.class, () -> groupServiceImpl.deleteById(10L));
        Exception expected = assertThrows(EmptyResultDataAccessException.class, () -> groupServiceImpl.deleteById(10L));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByName() throws SQLException {
        Group group = new Group(100L, "test");
        when(groupDao.findByName(group.getName())).thenReturn(Optional.of(group));
        Optional<Group> expected = groupDao.findByName("test");
        Optional<Group> actual = groupServiceImpl.findByName("test");
        assertEquals(expected, actual);
    }

    @Test
    void shouldCreateCourse() throws SQLException {
        Group group = new Group(100L, "test");
        when(groupDao.save(group)).thenReturn(group);
        Group expected = groupDao.save(group);
        Group actual = groupServiceImpl.save(group);
        assertEquals(expected, actual);
    }
}
