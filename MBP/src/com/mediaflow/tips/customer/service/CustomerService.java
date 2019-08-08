package com.mediaflow.tips.customer.service;

import java.util.List;

import com.mediaflow.tips.common.domain.Code;
import com.mediaflow.tips.common.domain.Customer;
import com.mediaflow.tips.common.domain.User;

public interface CustomerService {
	
	/** 
	 * 개수조회
	 */
	public int count(Customer customer);
	
	
	/**
	 * 목록조회
	 */
	public List<Customer> popList(Customer customer);

	/**
	 * 목록조회
	 */
	public List<Customer> list(Customer customer);
	
	/**
	 * 상세조회
	 */
	public Customer get(Customer customer);
	
	/**
	 * 추가
	 */
	public int add(Customer customer);
	
	/**
	 * 수정
	 */
	public int mod(Customer customer);
	
	/**
	 * 이메일체크 수
	 * @param customer
	 * @return
	 */
	public int countEmail(Customer customer);
}
