package com.mediaflow.tips.customer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mediaflow.tips.common.domain.ContractFile;

@Mapper
public interface ContractFileMapper {

		
	/**
	 * 목록조회
	 */
	public List<ContractFile> list(ContractFile contractFile);
	
	/**
	 * 상세조회
	 */
	public ContractFile get(ContractFile contractFile);
	
	/**
	 * 추가
	 */
	public int add(ContractFile contractFile);


	/**
	 * 삭제
	 */
	public int del(ContractFile contractFile);

	/**
	 * 삭제
	 */
	public int delfile(ContractFile contractFile);
		
}
