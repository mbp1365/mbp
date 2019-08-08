package com.mediaflow.tips.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediaflow.tips.common.domain.Brand;
import com.mediaflow.tips.customer.mapper.BrandMapper;

@Service("brandService")
public class BrandServiceImpl implements BrandService {
	
	@Autowired BrandMapper brandMapper;
	


	@Override
	public List<Brand> list(Brand brand) {
		return brandMapper.list(brand);
	}

	@Override
	public List<Brand> comboList(Brand brand) {
		return brandMapper.comboList(brand);
	}

	@Override
	public Brand get(Brand brand) {
		return brandMapper.get(brand);
	}

	@Override
	public int add(Brand brand) {
		return brandMapper.add(brand);
	}

	@Override
	public int mod(Brand brand) {
		return brandMapper.mod(brand);
	}

	@Override
	public int del(Brand brand) {
		return brandMapper.del(brand);
	}

	@Override
	public int count(Brand brand) {
		// TODO Auto-generated method stub
		return brandMapper.count(brand);
	}

	@Override
	public int countEmail(Brand brand) {
		// TODO Auto-generated method stub
		return brandMapper.countEmail(brand);
	}

}
