package com.foxminded.aprihodko.task10.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

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
import com.foxminded.aprihodko.task10.models.Role;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;
import com.foxminded.aprihodko.task10.services.UserService;

@WebMvcTest(controllers = { UserController.class })
class UserControllerTest {

    @MockBean
    UserService userService;

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
    void shouldGetListOfUsers() throws Exception {
        when(userService.findAll())
                .thenReturn(Arrays.asList(new User("teacher", UserType.TEACHER, Role.USER, "12345678"),
                        new User("student", UserType.STUDENT, Role.USER, "12345678"),
                        new User("none", UserType.NONE, Role.ADMIN, "12345678")));
        mvc.perform(get("/users/list")).andExpect(status().isOk())
                .andExpect(content().string(containsString("teacher")))
                .andExpect(content().string(containsString("student")))
                .andExpect(content().string(containsString("none")));
    }

    @Test
    @WithMockUser(authorities = "developers:read")
    void shouldAllowUserEdit() throws Exception {
        when(userService.findById(1L))
                .thenReturn(Optional.of(new User(1L, "John Wick", UserType.STUDENT, Role.USER, "")));

        mvc.perform(get("/users/edit/1")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser("test") // logged in, but no req authority
    void shouldDenyUserEdit() throws Exception {
        when(userService.findById(1L))
                .thenReturn(Optional.of(new User(1L, "John Wick", UserType.STUDENT, Role.USER, "")));

        mvc.perform(get("/users/edit/1")).andExpect(status().isForbidden());
    }
}
