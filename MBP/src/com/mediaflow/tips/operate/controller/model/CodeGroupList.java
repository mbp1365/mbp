package com.mediaflow.tips.operate.controller.model;

import java.io.Serializable;
import java.util.List;

import com.mediaflow.tips.common.domain.CodeGroup;
import com.mediaflow.tips.common.domain.User;

public class CodeGroupList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9200419121262880483L;
	List<CodeGroup>  codeGroupList;

	public List<CodeGroup> getCodeGroupList() {
		return codeGroupList;
	}

	public void setCodeGroupList(List<CodeGroup> codeGroupList) {
		this.codeGroupList = codeGroupList;
	}

	
	
}
