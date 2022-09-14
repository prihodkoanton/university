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
import org.springframework.test.web.servlet.MockMvc;

import com.foxminded.aprihodko.task10.dao.impl.CourseDaoImpl;
import com.foxminded.aprihodko.task10.dao.impl.GroupDaoImpl;
import com.foxminded.aprihodko.task10.dao.impl.LessonDaoImpl;
import com.foxminded.aprihodko.task10.dao.impl.RoomDaoImpl;
import com.foxminded.aprihodko.task10.dao.impl.UserDaoImpl;
import com.foxminded.aprihodko.task10.models.Room;

@WebMvcTest(controllers = { RoomController.class })
class RoomControllerTest {

    @MockBean
    CourseDaoImpl courseDaoImpl;

    @MockBean
    GroupDaoImpl groupDaoImpl;

    @MockBean
    LessonDaoImpl lessonDaoImpl;

    @MockBean
    RoomDaoImpl roomDaoImpl;

    @MockBean
    UserDaoImpl userDaoImpl;

    @Autowired
    MockMvc mvc;

    @Test
    void shouldGetListOfRooms() throws Exception {
        when(roomDaoImpl.findAll()).thenReturn(Arrays.asList(new Room("one"), new Room("two"), new Room("three")));
        mvc.perform(get("/rooms/list")).andExpect(status().isOk()).andExpect(content().string(containsString("one")))
                .andExpect(content().string(containsString("two")))
                .andExpect(content().string(containsString("three")));
    }
}
