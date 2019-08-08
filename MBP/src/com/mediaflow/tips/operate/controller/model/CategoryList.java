package com.mediaflow.tips.operate.controller.model;

import java.io.Serializable;
import java.util.List;

import com.mediaflow.tips.common.domain.Category;
import com.mediaflow.tips.common.domain.User;

public class CategoryList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3122320389142199302L;
	List<Category> categoryList;

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	
	
}
