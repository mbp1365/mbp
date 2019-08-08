package com.mediaflow.tips.operate.service;

import java.util.List;

import com.mediaflow.tips.common.domain.Category;

public interface CategoryService {
	
	/**
	 * 목록조회
	 */
	public List<Category> list(Category code);
	
	/**
	 * 상세조회
	 */
	public Category get(Category code);
	
	/**
	 * 추가
	 */
	public int add(Category code);

	/**
	 * 수정
	 */
	public int mod(Category code);
	
	/**
	 * 삭제
	 */
	public int del(Category code);

	/**
	 * 목록조회
	 */
	public List<Category> catelist(Category code);
}
