package com.mediaflow.tips.operate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediaflow.tips.common.domain.Category;
import com.mediaflow.tips.common.domain.Code;
import com.mediaflow.tips.common.mapper.CodeMapper;
import com.mediaflow.tips.operate.mapper.CategoryMapper;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired CategoryMapper categoryMapper;

	@Override
	public List<Category> list(Category cate) {
		return categoryMapper.list(cate);
	}

	@Override
	public Category get(Category cate) {
		return categoryMapper.get(cate);
	}

	@Override
	public int add(Category cate) {
		return categoryMapper.add(cate);
	}

	@Override
	public int mod(Category cate) {
		return categoryMapper.mod(cate);
	}
	
	@Override
	public int del(Category cate) {
		return categoryMapper.del(cate);
	}
	
	@Override
	public List<Category> catelist(Category cate) {
		return categoryMapper.catelist(cate);
	}

}
