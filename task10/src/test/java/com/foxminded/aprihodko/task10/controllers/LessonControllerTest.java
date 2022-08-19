package com.foxminded.aprihodko.task10.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.DayOfWeek;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.foxminded.aprihodko.task10.models.Lesson;
import com.foxminded.aprihodko.task10.services.CourseService;
import com.foxminded.aprihodko.task10.services.GroupService;
import com.foxminded.aprihodko.task10.services.LessonService;
import com.foxminded.aprihodko.task10.services.RoomService;
import com.foxminded.aprihodko.task10.services.UserService;

@WebMvcTest(controllers = { LessonController.class })
class LessonControllerTest {

    @MockBean
    LessonService lessonService;

    @MockBean
    GroupService groupService;

    @MockBean
    CourseService courseService;

    @MockBean
    UserService userService;

    @MockBean
    RoomService roomService;

    @Autowired
    MockMvc mvc;

    @Test
    void shouldGetListOfLessons() throws Exception {
        when(lessonService.findAll()).thenReturn(Arrays.asList(new Lesson(DayOfWeek.MONDAY, 1, 100L, 100L, 100L, 100L),
                new Lesson(DayOfWeek.TUESDAY, 1, 101L, 101L, 101L, 101L),
                new Lesson(DayOfWeek.WEDNESDAY, 1, 102L, 102L, 102L, 102L)));
        mvc.perform(get("/lessons/list")).andExpect(status().isOk())
                .andExpect(content().string(containsString("MONDAY")))
                .andExpect(content().string(containsString("TUESDAY")))
                .andExpect(content().string(containsString("WEDNESDAY")));
    }
}
