package com.mediaflow.tips.customer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mediaflow.tips.common.domain.Contract;

@Mapper
public interface ContractMapper {

	/**
	 * 개수조회
	 */
	public int count(Contract contract);
	
	/**
	 * 목록조회
	 */
	public List<Contract> list(Contract contract);
	
	/**
	 * 상세조회
	 */
	public Contract get(Contract contract);
	
	/**
	 * 추가
	 */
	public int add(Contract contract);

	/**
	 * 수정
	 */
	public int mod(Contract contract);

	/**
	 * 수정
	 */
	public int del(Contract contract);
		
}
