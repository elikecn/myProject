package com.ixiaofan.service;

import org.springframework.stereotype.Service;

import com.ixiaofan.entity.FileMD5;

@Service
public interface FileMD5Service {
	
	public FileMD5 findOne(String postfix,String stringmd5);
	
	void save(FileMD5 fileMD5);
}
