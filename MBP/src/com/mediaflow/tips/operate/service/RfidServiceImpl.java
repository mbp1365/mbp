package com.mediaflow.tips.operate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediaflow.tips.common.domain.Rfid;
import com.mediaflow.tips.operate.mapper.RfidMapper;

@Service("rfidService")
public class RfidServiceImpl implements RfidService {
	
	@Autowired RfidMapper rfidMapper;

	

	@Override
	public int count(Rfid rfid) {
		// TODO Auto-generated method stub
		return rfidMapper.count(rfid);
	}

	@Override
	public List<Rfid> list(Rfid rfid) {
		return rfidMapper.list(rfid);
	}

	@Override
	public Rfid get(Rfid rfid) {
		return rfidMapper.get(rfid);
	}

	@Override
	public int add(Rfid rfid) {
		return rfidMapper.add(rfid);
	}

	@Override
	public int mod(Rfid rfid) {
		return rfidMapper.mod(rfid);
	}
	
//	@Override
//	public int del(Rfid rfid) {
//		return rfidMapper.del(rfid);
//	}
}
