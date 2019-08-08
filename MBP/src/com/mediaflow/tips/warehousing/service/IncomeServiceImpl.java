package com.mediaflow.tips.warehousing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediaflow.tips.common.domain.Product;
import com.mediaflow.tips.common.domain.User;
import com.mediaflow.tips.user.mapper.UserMapper;
import com.mediaflow.tips.warehousing.mapper.IncomeMapper;
import com.mediaflow.tips.warehousing.mapper.ProductMapper;

@Service("incomeService")
public class IncomeServiceImpl implements IncomeService {
	
	@Autowired IncomeMapper incomeMapper;

	@Override
	public int count(Product product) {
		return incomeMapper.count(product);
	}

	@Override
	public List<Product> list(Product product) {
		return incomeMapper.list(product);
	}

	@Override
	public Product get(Product product) {
		return incomeMapper.get(product);
	}

	@Override
	public int add(Product product) {
		return incomeMapper.add(product);
	}

	@Override
	public int mod(Product product) {
		return incomeMapper.mod(product);
	}
}
