package com.mediaflow.tips.customer.controller.model;

import java.io.Serializable;
import java.util.List;

import com.mediaflow.tips.common.domain.Customer;

public class CustomerList implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 630080138079165408L;
	
	
	private List<Customer>  customerList;
	
	
	public List<Customer> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}
	
	

	
	
}
