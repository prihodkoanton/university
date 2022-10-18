package com.foxminded.aprihodko.task10.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.foxminded.aprihodko.task10.dao.CourseDao;
import com.foxminded.aprihodko.task10.dao.GroupDao;
import com.foxminded.aprihodko.task10.dao.LessonDao;
import com.foxminded.aprihodko.task10.dao.RoomDao;
import com.foxminded.aprihodko.task10.dao.UserDao;
import com.foxminded.aprihodko.task10.models.Course;

@WebMvcTest(controllers = { CourseController.class })
class CourseControllerTest {

    @MockBean
    CourseDao courseDaoImpl;

    @MockBean
    GroupDao groupDaoImpl;

    @MockBean
    LessonDao lessonDaoImpl;

    @MockBean
    RoomDao roomDaoImpl;

    @MockBean
    UserDao userDaoImpl;

    @Autowired
    MockMvc mvc;

    @Test
    @WithMockUser("test")
    void shouldGetListOfCourses() throws Exception {
        when(courseDaoImpl.findAll()).thenReturn(Arrays.asList(new Course("one", "course one"),
                new Course("two", "course two"), new Course("three", "course three")));
        mvc.perform(get("/courses/list")).andExpect(status().isOk())
                .andExpect(content().string(containsString("course one")))
                .andExpect(content().string(containsString("course two")))
                .andExpect(content().string(containsString("course three")));
    }
}
