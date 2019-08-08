package com.mediaflow.tips.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediaflow.tips.common.domain.User;
import com.mediaflow.tips.user.mapper.UserMapper;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired UserMapper userMapper;

	@Override
	public int count(User user) {
		return userMapper.count(user);
	}

	@Override
	public List<User> list(User user) {
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
