package com.foxminded.aprihodko.task10.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foxminded.aprihodko.task10.models.Room;

public interface RoomDao extends JpaRepository<Room, Long> {

    Optional<Room> findByTitle(String title);
}
