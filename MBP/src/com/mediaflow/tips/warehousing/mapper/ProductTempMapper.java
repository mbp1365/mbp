package com.mediaflow.tips.warehousing.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mediaflow.tips.common.domain.ProductTemp;

@Mapper
public interface ProductTempMapper {

	/**
	 * 개수조회
	 */
	public int count(ProductTemp productTemp);
	
	/**
	 * 목록조회
	 */
	public List<ProductTemp> list(ProductTemp productTemp);
	
	/**
	 * 상세조회
	 */
	public ProductTemp get(ProductTemp productTemp);

	/**
	 * 추가
	 */
	public int add(ProductTemp productTemp);

	/**
	 * 저장 (ProductTemp -> Product로 이관 저장)
	 */
	public int save(ProductTemp productTemp);
	
	/**
	 * 수정
	 */
	public int del(ProductTemp productTemp);
		
}
