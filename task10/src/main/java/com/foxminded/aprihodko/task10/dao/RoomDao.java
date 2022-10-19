package com.foxminded.aprihodko.task10.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foxminded.aprihodko.task10.models.Room;

@Repository
public interface RoomDao extends JpaRepository<Room, Long> {

	Optional<Room> findByTitle(String title);
}
