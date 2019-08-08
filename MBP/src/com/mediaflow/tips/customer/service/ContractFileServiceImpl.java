package com.mediaflow.tips.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediaflow.tips.common.domain.Contract;
import com.mediaflow.tips.common.domain.ContractFile;
import com.mediaflow.tips.customer.mapper.ContractFileMapper;
import com.mediaflow.tips.customer.mapper.ContractMapper;

@Service("contractFileService")
public class ContractFileServiceImpl implements ContractFileService {
	
	@Autowired ContractFileMapper contractFileMapper;
	
	

	@Override
	public List<ContractFile> list(ContractFile contractFile) {
		return contractFileMapper.list(contractFile);
	}

	@Override
	public ContractFile get(ContractFile contractFile) {
		return contractFileMapper.get(contractFile);
	}

	@Override
	public int add(ContractFile contractFile) {
		return contractFileMapper.add(contractFile);
	}

	@Override
	public int del(ContractFile contractFile) {
		return contractFileMapper.del(contractFile);
	}


	@Override
	public int delfile(ContractFile contractFile) {
		return contractFileMapper.delfile(contractFile);
	}

}
