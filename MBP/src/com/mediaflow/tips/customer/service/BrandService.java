package com.mediaflow.tips.customer.service;

import java.util.List;

import com.mediaflow.tips.common.domain.Brand;

public interface BrandService {
	
	/** 
	 * 개수조회
	 */
	public int count(Brand brand);
	
	
	/**
	 * 목록조회
	 */
	public List<Brand> list(Brand brand);

	public List<Brand> comboList(Brand brand);
	
	/**
	 * 상세조회
	 */
	public Brand get(Brand brand);
	
	/**
	 * 추가
	 */
	public int add(Brand brand);
	
	/**
	 * 수정
	 */
	public int mod(Brand brand);
	
	
	/**
	 * 삭제(수정 update)
	 */
	public int del(Brand brand);
	
	/**
	 * 이메일체크 수
	 * @param customer
	 * @return
	 */
	public int countEmail(Brand brand);
}
