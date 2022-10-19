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

import com.foxminded.aprihodko.task10.models.Room;
import com.foxminded.aprihodko.task10.repositories.CourseRepository;
import com.foxminded.aprihodko.task10.repositories.GroupRepository;
import com.foxminded.aprihodko.task10.repositories.LessonRepository;
import com.foxminded.aprihodko.task10.repositories.RoomRepository;
import com.foxminded.aprihodko.task10.repositories.UserRepository;

@WebMvcTest(controllers = { RoomController.class })
class RoomControllerTest {

	@MockBean
	CourseRepository courseRepository;

	@MockBean
	GroupRepository groupDaoImpl;

	@MockBean
	LessonRepository lessonRepository;

	@MockBean
	RoomRepository roomRepository;

	@MockBean
	UserRepository userRepository;

	@Autowired
	MockMvc mvc;

	@Test
	@WithMockUser("test")
	void shouldGetListOfRooms() throws Exception {
		when(roomRepository.findAll()).thenReturn(Arrays.asList(new Room("one"), new Room("two"), new Room("three")));
		mvc.perform(get("/rooms/list")).andExpect(status().isOk()).andExpect(content().string(containsString("one")))
				.andExpect(content().string(containsString("two"))).andExpect(content().string(containsString("three")));
	}
}
