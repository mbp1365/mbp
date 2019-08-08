package com.mediaflow.tips.warehousing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediaflow.tips.common.domain.Product;
import com.mediaflow.tips.common.domain.User;
import com.mediaflow.tips.user.mapper.UserMapper;
import com.mediaflow.tips.warehousing.mapper.ProductMapper;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired ProductMapper productMapper;

	@Override
	public int count(Product product) {
		return productMapper.count(product);
	}

	@Override
	public List<Product> list(Product product) {
		return productMapper.list(product);
	}

	@Override
	public Product get(Product product) {
		return productMapper.get(product);
	}

	@Override
	public int add(Product product) {
		return productMapper.add(product);
	}

	@Override
	public int mod(Product product) {
		return productMapper.mod(product);
	}
}
