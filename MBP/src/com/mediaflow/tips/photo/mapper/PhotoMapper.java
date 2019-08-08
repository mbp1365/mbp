package com.mediaflow.tips.photo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mediaflow.tips.common.domain.User;

@Mapper
public interface PhotoMapper {

	/**
	 * 개수조회
	 */
	public int count(User user);
	
	/**
	 * 목록조회
	 */
	public List<User> list(User user);
	
	/**
	 * 상세조회
	 */
	public User get(User user);
	
	/**
	 * 추가
	 */
	public int add(User user);
	
	/**
	 * 수정
	 */
	public int mod(User user);
		
}
