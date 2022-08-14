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

import com.foxminded.aprihodko.task10.dao.impl.UserDaoImpl;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;

@WebMvcTest(controllers = { UserController.class })
class UserControllerTest {

    @MockBean
    UserDaoImpl userDaoImpl;

    @Autowired
    MockMvc mvc;

    @Test
    void shouldGetListOfUsers() throws Exception {
        when(userDaoImpl.findAll()).thenReturn(Arrays.asList(new User("teacher", UserType.TEACHER),
                new User("student", UserType.STUDENT), new User("none", UserType.NONE)));
        mvc.perform(get("/users/list")).andExpect(status().isOk())
                .andExpect(content().string(containsString("teacher")))
                .andExpect(content().string(containsString("student")))
                .andExpect(content().string(containsString("none")));
    }
}
