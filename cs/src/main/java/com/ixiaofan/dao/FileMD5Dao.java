package com.ixiaofan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ixiaofan.entity.FileMD5;

@Repository
public interface FileMD5Dao extends JpaRepository<FileMD5, Long>{
	
	public FileMD5 findOneByPostfixAndStringmd5(String postfix,String stringmd5);
}
