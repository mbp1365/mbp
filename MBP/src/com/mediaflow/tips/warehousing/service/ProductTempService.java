package com.mediaflow.tips.warehousing.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.mediaflow.tips.common.domain.ProductTemp;

public interface ProductTempService {
	/** 
	 * 개수조회
	 */
	public int count(ProductTemp productTemp);
	
	/**
	 * 목록조회
	 */
	public List<ProductTemp> list(ProductTemp productTemp);
	
	/**
	 * 상세조회
	 */
	public ProductTemp get(ProductTemp productTemp);

	/**
	 * 추가
	 */
	public int add(ProductTemp productTemp);
	
	/**
	 * 삭제
	 */
	public int del(ProductTemp productTemp);
	
	public List<ProductTemp> loadXlsx(String fileId, String fullFilePath) throws InvalidFormatException, IOException;
}
