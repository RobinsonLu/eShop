package cn.haohaowo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.haohaowo.entity.Account;
import cn.haohaowo.hibernate.dao.AccountDao;
import cn.haohaowo.service.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDao accountDao;

	public boolean emailExist(Integer id, String email) {
		return accountDao.emailExist(id, email);
	}

	public Account login(String username, String password) {
		Account account = accountDao.getAccount(username); 
		if(null != account && password.equals(account.getPassword())){
			return account;
		}else{
			return null;
		}
	}

	public void register(Account account) {
		accountDao.createAccount(account);
	}

	public void saveAccount(Account account) {
		accountDao.createAccount(account);
	}

	public boolean usernameExist(String username) {
		Account account = accountDao.getAccount(username);
		if(null != username && username.equals(account.getUsername())){
			return true;
		}else{
			return false;
		}
	}
	
	

}
