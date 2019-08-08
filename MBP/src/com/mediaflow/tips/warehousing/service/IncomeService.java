package com.mediaflow.tips.warehousing.service;

import java.util.List;

import com.mediaflow.tips.common.domain.Product;
import com.mediaflow.tips.common.domain.User;

public interface IncomeService {
	/** 
	 * 개수조회
	 */
	public int count(Product product);
	
	/**
	 * 목록조회
	 */
	public List<Product> list(Product product);
	
	/**
	 * 상세조회
	 */
	public Product get(Product product);
	
	/**
	 * 추가
	 */
	public int add(Product product);
	
	/**
	 * 수정
	 */
	public int mod(Product product);
}
