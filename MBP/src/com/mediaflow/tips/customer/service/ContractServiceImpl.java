package com.mediaflow.tips.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediaflow.tips.common.domain.Contract;
import com.mediaflow.tips.customer.mapper.ContractMapper;

@Service("contractService")
public class ContractServiceImpl implements ContractService {
	
	@Autowired ContractMapper contractMapper;
	
	

	@Override
	public List<Contract> list(Contract contract) {
		return contractMapper.list(contract);
	}

	@Override
	public Contract get(Contract contract) {
		return contractMapper.get(contract);
	}

	@Override
	public int add(Contract contract) {
		return contractMapper.add(contract);
	}

	@Override
	public int mod(Contract contract) {
		return contractMapper.mod(contract);
	}

	@Override
	public int del(Contract contract) {
		return contractMapper.del(contract);
	}

	@Override
	public int count(Contract contract) {
		// TODO Auto-generated method stub
		return contractMapper.count(contract);
	}

	@Override
	public int countByName(Contract contract) {
		// TODO Auto-generated method stub
		return contractMapper.count(contract);
	}


}
