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

import com.foxminded.aprihodko.task10.dao.impl.CourseDaoImpl;
import com.foxminded.aprihodko.task10.dao.impl.GroupDaoImpl;
import com.foxminded.aprihodko.task10.dao.impl.LessonDaoImpl;
import com.foxminded.aprihodko.task10.dao.impl.RoomDaoImpl;
import com.foxminded.aprihodko.task10.dao.impl.UserDaoImpl;
import com.foxminded.aprihodko.task10.models.Group;

@WebMvcTest(controllers = { GroupController.class })
class GroupControllerTest {

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
    @WithMockUser("test")
    void shouldGetListOfGroups() throws Exception {
        when(groupDaoImpl.findAll()).thenReturn(Arrays.asList(new Group("group for Java"),
                new Group("group for Java Spring"), new Group("group for C# .Net")));
        mvc.perform(get("/groups/list")).andExpect(status().isOk())
                .andExpect(content().string(containsString("group for Java")))
                .andExpect(content().string(containsString("group for Java Spring")))
                .andExpect(content().string(containsString("group for C# .Net")));
    }
}
