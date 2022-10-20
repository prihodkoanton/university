package com.foxminded.aprihodko.task10.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.task10.models.Group;
import com.foxminded.aprihodko.task10.repositories.GroupRepository;
import com.foxminded.aprihodko.task10.services.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

	private final GroupRepository groupRepository;

	public GroupServiceImpl(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Group> findById(Long id) throws SQLException {
		return groupRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Group> findAll() throws SQLException {
		return groupRepository.findAll();
	}

	@Override
	@Transactional
	public void deleteById(Long id) throws SQLException {
		groupRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Group> findByName(String name) throws SQLException {
		return groupRepository.findByName(name);
	}

	@Override
	@Transactional
	public Group save(Group entity) throws SQLException {
		return groupRepository.save(entity);
	}
}
