package com.mediaflow.tips.operate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mediaflow.tips.common.domain.Category;

@Mapper
public interface CategoryMapper {

	/**
	 * 개수조회
	 */
	public int count(Category category);
	
	/**
	 * 목록조회
	 */
	public List<Category> list(Category category);
	
	/**
	 * 상세조회
	 */
	public Category get(Category category);
	
	/**
	 * 추가
	 */
	public int add(Category category);
	
	/**
	 * 수정
	 */
	public int mod(Category category);
	
	/**
	 * 삭제 useYn = N으로 수정.
	 */
	public int del(Category category);

	/**
	 * category 조회
	 */
	public List<Category> catelist(Category category);
}
