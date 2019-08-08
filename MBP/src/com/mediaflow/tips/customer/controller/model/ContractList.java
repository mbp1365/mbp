package com.mediaflow.tips.customer.controller.model;

import java.io.Serializable;
import java.util.List;

import com.mediaflow.tips.common.domain.Contract;

public class ContractList implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -763346451682850030L;
	
	
	private List<Contract>  contractList;


	public List<Contract> getContractList() {
		return contractList;
	}


	public void setContractList(List<Contract> contractList) {
		this.contractList = contractList;
	}
	
	
}
