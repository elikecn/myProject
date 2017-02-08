package com.ixiaofan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ixiaofan.entity.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long>{
	
	public Account  findOneByUsername(String username);
	
	public Account findOneByEmail(String email);
	
	public Account findOneByCard(String card);
	
}
