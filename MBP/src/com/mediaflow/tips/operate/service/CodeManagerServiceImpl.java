package com.mediaflow.tips.operate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediaflow.tips.common.domain.Code;
import com.mediaflow.tips.common.domain.CodeGroup;
import com.mediaflow.tips.operate.mapper.CodeManagerMapper;

@Service("codeManagerService")
public class CodeManagerServiceImpl implements CodeManagerService {
	
	@Autowired CodeManagerMapper codeManagerMapper;

	@Override
	public List<Code> codeList(Code code) {
		return codeManagerMapper.codeList(code);
	}
	

	@Override
	public List<CodeGroup> codeGroupList(CodeGroup codeGroup) {
		return codeManagerMapper.codeGroupList(codeGroup);
	}

	@Override
	public CodeGroup getCodeGroup(CodeGroup codeGroup) {
		return codeManagerMapper.getCodeGroup(codeGroup);
	}

	@Override
	public Code getCode(Code code) {
		return codeManagerMapper.getCode(code);
	}

	@Override
	public int addCodeGroup(CodeGroup codeGroup) {
		return codeManagerMapper.addCodeGroup(codeGroup);
	}

	@Override
	public int addCode(Code code) {
		return codeManagerMapper.addCode(code);
	}
	
	@Override
	public int modCodeGroup(CodeGroup codeGroup) {
		return codeManagerMapper.modCodeGroup(codeGroup);
	}

	@Override
	public int modCode(Code code) {
		return codeManagerMapper.modCode(code);
	}
}
