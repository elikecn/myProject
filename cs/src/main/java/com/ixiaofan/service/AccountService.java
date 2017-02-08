package com.ixiaofan.service;

import org.springframework.stereotype.Service;

import com.ixiaofan.entity.Account;

@Service
public interface AccountService {
	
	public void save(Account account);
	
	public Account findOneByUsername(String username);
	
	public void delete(Account account);
	
	public Account findOneByCard(String card);
	
	public Account findOneByEmail(String email);
}
