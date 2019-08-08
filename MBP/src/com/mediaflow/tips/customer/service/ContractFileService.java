package com.mediaflow.tips.customer.service;

import java.util.List;

import com.mediaflow.tips.common.domain.Contract;
import com.mediaflow.tips.common.domain.ContractFile;

public interface ContractFileService {
	

	
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
	 * 삭제(수정 update)
	 */
	public int del(ContractFile contractFile);

	/**
	 * 삭제(수정 update)
	 */
	public int delfile(ContractFile contractFile);
	

}
