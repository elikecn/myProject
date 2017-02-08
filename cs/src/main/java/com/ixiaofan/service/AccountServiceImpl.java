package com.ixiaofan.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ixiaofan.dao.AccountDao;
import com.ixiaofan.entity.Account;
import com.ixiaofan.tools.Md5;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountDao accountDao;
	
	public void save(Account account) {
		String p = account.getPassword();
		account.setPassword(Md5.stringmd5(p));
		String c = account.getCard();
		int i = 0;
		if(c.length() == 15){
			i = Integer.parseInt(String.valueOf(c.charAt(14)));
		}else if(c.length() == 18){
			i = Integer.parseInt(String.valueOf(c.charAt(16)));
		}
		if(i%2 == 1){
			account.setSex("1");
		}else {
			account.setSex("0");
		}
		Date date = new Date();
		account.setCreattime(date);
		account.setUserlv(3);
		if(StringUtils.isBlank(String.valueOf(account.getStatus()))){
			account.setStatus(0);
		}
		accountDao.save(account);
	}

	public Account findOneByUsername(String username) {
		Account account = accountDao.findOneByUsername(username);
		return account;
	}

	public void delete(Account account) {
		accountDao.delete(account);
	}

	public Account findOneByCard(String card) {
		return accountDao.findOneByCard(card);
	}

	public Account findOneByEmail(String email) {
		return accountDao.findOneByEmail(email);
	}

}
