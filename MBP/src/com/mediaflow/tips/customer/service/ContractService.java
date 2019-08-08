package com.mediaflow.tips.customer.service;

import java.util.List;

import com.mediaflow.tips.common.domain.Contract;

public interface ContractService {
	
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
	 * 삭제(수정 update)
	 */
	public int del(Contract contract);
	
	/**
	 * 이메일체크 수
	 * @param customer
	 * @return
	 */
	public int countByName(Contract contract);
}
