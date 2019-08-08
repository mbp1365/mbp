package com.mediaflow.tips.operate.controller.model;

import java.io.Serializable;
import java.util.List;

import com.mediaflow.tips.common.domain.Code;
import com.mediaflow.tips.common.domain.CodeGroup;
import com.mediaflow.tips.common.domain.User;

public class CodeList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7613740713485801479L;

	private List<Code>  codeList;
	
	public List<Code> getCodeList() {
		return codeList;
	}
	public void setCodeList(List<Code> codeList) {
		this.codeList = codeList;
	}

	
	
}
