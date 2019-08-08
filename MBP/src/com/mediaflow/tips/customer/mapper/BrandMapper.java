package com.mediaflow.tips.customer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mediaflow.tips.common.domain.Brand;
import com.mediaflow.tips.common.domain.Customer;
import com.mediaflow.tips.common.domain.User;

@Mapper
public interface BrandMapper {

	/**
	 * 개수조회
	 */
	public int count(Brand brand);
	
	/**
	 * 목록조회
	 */
	public List<Brand> list(Brand brand);
	
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
	 * 수정
	 */
	public int del(Brand brand);
	
	/**
	 * 이메일체크 수
	 */
	public int countEmail(Brand brand);

	/**
	 * combobox 목록조회
	 */
	public List<Brand> comboList(Brand brand);
}
