package com.mediaflow.tips.operate.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediaflow.tips.common.domain.User;
import com.mediaflow.tips.operate.controller.UserManagerController;
import com.mediaflow.tips.operate.mapper.UserManagerMapper;

@Service("userManagerService")
public class UserManagerServiceImpl implements UserManagerService {

	private static final Logger logger = LoggerFactory.getLogger(UserManagerServiceImpl.class);
	
	@Autowired UserManagerMapper userMapper;

	@Override
	public int count(User user) {
		return userMapper.count(user);
	}

	@Override
	public List<User> list(User user) {
		logger.debug("user: "+user);
		return userMapper.list(user);
	}

	@Override
	public User get(User user) {
		return userMapper.get(user);
	}

	@Override
	public int add(User user) {
		return userMapper.add(user);
	}

	@Override
	public int mod(User user) {
		return userMapper.mod(user);
	}
}
