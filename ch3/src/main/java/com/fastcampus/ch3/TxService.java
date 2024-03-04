package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxService {
	@Autowired
	A1Dao a1Dao;
	
	@Autowired
	B1Dao b1Dao;
	
	public void insertWithoutTx() throws Exception {
		a1Dao.insert("1", "100");
		a1Dao.insert("1", "200");
	}
	
	@Transactional(rollbackFor = Exception.class)
//	@Transactional // RuntimeException, Error만 rollback
	public void insertWithoutTxFail() throws Exception {
		a1Dao.insert("1", "100"); // 성공
//		throw new RuntimeException(); // 롤백O
//		throw new Exception(); // 롤백 X
		a1Dao.insert("1", "200"); // 실패
	}
	
	@Transactional
	public void insertWithTxSuccess() throws Exception {
		a1Dao.insert("1", "100");
		a1Dao.insert("2", "200");
	}
}
