package com.mediaflow.tips.customer.controller.model;

import java.io.Serializable;
import java.util.List;

import com.mediaflow.tips.common.domain.Brand;

public class BrandList implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -763346451682850030L;
	
	
	private List<Brand>  brandList;


	public List<Brand> getBrandList() {
		return brandList;
	}


	public void setBrandList(List<Brand> brandList) {
		this.brandList = brandList;
	}
	
	
	

	
	
}
