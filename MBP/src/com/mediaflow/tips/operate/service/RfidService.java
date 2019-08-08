package com.mediaflow.tips.operate.service;

import java.util.List;

import com.mediaflow.tips.common.domain.Rfid;

public interface RfidService {

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
	
	/**
	 * 삭제
	 */
//	public int del(Rfid rfid);
}
