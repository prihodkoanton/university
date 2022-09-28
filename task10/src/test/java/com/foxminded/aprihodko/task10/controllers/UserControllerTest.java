package com.foxminded.aprihodko.task10.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.foxminded.aprihodko.task10.dao.impl.CourseDaoImpl;
import com.foxminded.aprihodko.task10.dao.impl.GroupDaoImpl;
import com.foxminded.aprihodko.task10.dao.impl.LessonDaoImpl;
import com.foxminded.aprihodko.task10.dao.impl.RoomDaoImpl;
import com.foxminded.aprihodko.task10.dao.impl.UserDaoImpl;
import com.foxminded.aprihodko.task10.models.Role;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;
import com.foxminded.aprihodko.task10.services.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = { UserController.class })
class UserControllerTest {

    @MockBean
    UserService userServiceImpl;

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
    void shouldGetListOfUsers() throws Exception {
        when(userServiceImpl.findAll())
                .thenReturn(Arrays.asList(new User("teacher", UserType.TEACHER, Role.USER, "12345678"),
                        new User("student", UserType.STUDENT, Role.USER, "12345678"),
                        new User("none", UserType.NONE, Role.ADMIN, "12345678")));
        mvc.perform(get("/users/list")).andExpect(status().isOk())
                .andExpect(content().string(containsString("teacher")))
                .andExpect(content().string(containsString("student")))
                .andExpect(content().string(containsString("none")));
    }
}
