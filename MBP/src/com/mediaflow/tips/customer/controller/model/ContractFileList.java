package com.mediaflow.tips.customer.controller.model;

import java.io.Serializable;
import java.util.List;

import com.mediaflow.tips.common.domain.Contract;
import com.mediaflow.tips.common.domain.ContractFile;

public class ContractFileList implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7151132746534984691L;

	private List<ContractFile>  contractFileList;
	private String  cortId;


	public List<ContractFile> getContractFileList() {
		return contractFileList;
	}


	public void setContractFileList(List<ContractFile> contractFileList) {
		this.contractFileList = contractFileList;
	}


	public String getCortId() {
		return cortId;
	}


	public void setCortId(String cortId) {
		this.cortId = cortId;
	}


	
	
}
