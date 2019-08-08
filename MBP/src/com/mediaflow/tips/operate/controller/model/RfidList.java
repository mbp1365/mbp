package com.mediaflow.tips.operate.controller.model;

import java.io.Serializable;
import java.util.List;

import com.mediaflow.tips.common.domain.Category;
import com.mediaflow.tips.common.domain.Rfid;
import com.mediaflow.tips.common.domain.User;

public class RfidList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1561951017677098860L;
	/**
	 * 
	 */
	
	
	
	List<Rfid> rfidList;
	public List<Rfid> getRfidList() {
		return rfidList;
	}
	public void setRfidList(List<Rfid> rfidList) {
		this.rfidList = rfidList;
	}

	
	
	
}
