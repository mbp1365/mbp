package com.mediaflow.tips.operate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mediaflow.tips.common.domain.Category;
import com.mediaflow.tips.common.domain.Rfid;

@Mapper
public interface RfidMapper {

	/**
	 * 개수조회
	 */
	public int count(Rfid rfid);
	
	/**
	 * 목록조회
	 */
	public List<Rfid> list(Rfid rfid);
	
	/**
	 * 상세조회
	 */
	public Rfid get(Rfid rfid);
	
	/**
	 * 추가
	 */
	public int add(Rfid rfid);
	
	/**
	 * 수정
	 */
	public int mod(Rfid rfid);
	
}
