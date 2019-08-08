package com.mediaflow.tips.operate.service;

import java.util.List;

import com.mediaflow.tips.common.domain.Code;
import com.mediaflow.tips.common.domain.CodeGroup;

public interface CodeManagerService {
	
	/**
	 * 그룹 코드 목록조회
	 */
	public List<CodeGroup> codeGroupList(CodeGroup codeGroup);
	
	/**
	 * 목록조회
	 */
	public List<Code> codeList(Code code);
	
	/**
	 * 상세조회
	 */
	public CodeGroup getCodeGroup(CodeGroup codeGroup);
	
	/**
	 * 상세조회
	 */
	public Code getCode(Code code);
	
	/**
	 * 추가
	 */
	public int addCodeGroup(CodeGroup codeGroup);
	

	/**
	 * 추가
	 */
	public int addCode(Code code);
	
	/**
	 * 수정
	 */
	public int modCodeGroup(CodeGroup codeGroup);

	/**
	 * 수정
	 */
	public int modCode(Code code);
}
