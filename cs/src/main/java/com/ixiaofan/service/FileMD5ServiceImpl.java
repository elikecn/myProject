package com.ixiaofan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ixiaofan.dao.FileMD5Dao;
import com.ixiaofan.entity.FileMD5;

@Service
public class FileMD5ServiceImpl implements FileMD5Service{

	@Autowired
	private FileMD5Dao fileMD5Dao;
	
	@Override
	public FileMD5 findOne(String postfix,String stringmd5) {
		return fileMD5Dao.findOneByPostfixAndStringmd5(postfix, stringmd5);
	}

	@Override
	public void save(FileMD5 fileMD5) {
		fileMD5Dao.save(fileMD5);
	}

}
