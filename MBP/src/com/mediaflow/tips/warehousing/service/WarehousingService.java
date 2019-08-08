package com.mediaflow.tips.warehousing.service;

import java.util.List;

import com.mediaflow.tips.common.domain.User;

public interface WarehousingService {
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
